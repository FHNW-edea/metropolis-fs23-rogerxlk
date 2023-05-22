package metropolis.xtracted.repository

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.time.LocalDate
import java.util.logging.Logger
import kotlin.time.Duration
import metropolis.xtracted.data.DbColumn
import metropolis.xtracted.data.Filter
import metropolis.xtracted.data.SortDirective
import metropolis.xtracted.data.asSql

fun <T> readFirst(url: String, table: String, columns: String = "*", where: String , map: ResultSet.() -> T?) : T? =
    DriverManager.getConnection(url) //TODO: opening a connection is pretty expensive. Introduce a ConnectionPool that reuses connections.
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "SELECT $columns FROM $table WHERE $where"

            try {
                val start = System.currentTimeMillis()

                val resultSet = it.createStatement()
                    .executeQuery(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())

                if (resultSet.next()) {
                    resultSet.map()
                } else {
                    null
                }
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun readIds(url: String, table: String, idColumn: DbColumn, filters: List<Filter<*>>, sortDirective: SortDirective) : List<Int> =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val orderBy = if(null == sortDirective.column) "" else "ORDER BY  ${sortDirective.column}  ${sortDirective.direction}"

            val sql = "SELECT ${idColumn} FROM $table ${filters.asSql()} $orderBy"

            try {
                val start = System.currentTimeMillis()

                val resultSet = it.createStatement()
                    .executeQuery(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())

                buildList {
                    while (resultSet.next()) {
                        add(resultSet.getInt(1))
                    }
                }
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun <T> readAll(url: String, table: String, columns: String = "*", where: String = "", orderBy : String = "ID", map: ResultSet.() -> T) : List<T> =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "SELECT $columns FROM $table ${if (where.isNotEmpty()) "WHERE $where" else ""} ORDER BY $orderBy ASC"

            try {
                val start = System.currentTimeMillis()

                val resultSet = it.createStatement()
                    .executeQuery(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())

                buildList {
                    while (resultSet.next()) {
                        add(resultSet.map())
                    }
                }
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun update(url: String, table: String, id: Int, setStatement: String, idColumn: DbColumn) : Unit =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "UPDATE $table $setStatement WHERE $idColumn = $id"

            try {
                val start = System.currentTimeMillis()

                try {
                    it.createStatement()
                        .executeUpdate(sql)
                } catch (e: SQLException) {
                    logger.severe { e.localizedMessage }
                }

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun count(url: String, table: String, idColumn: DbColumn) : Int =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "SELECT COUNT(${idColumn}) FROM $table"

            try {
                val start = System.currentTimeMillis()

                val resultSet = it.createStatement()
                    .executeQuery(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())

                resultSet.next()

                resultSet.getInt(1)
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun count(url : String, table: String, filters: List<Filter<*>>, idColumn: DbColumn) : Int =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "SELECT COUNT(${idColumn}) FROM $table ${filters.asSql()}"

            try {
                val start = System.currentTimeMillis()

                val resultSet = it.createStatement()
                    .executeQuery(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())

                resultSet.next()

                resultSet.getInt(1)
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun insertAndCreateKey(url: String, insertStmt: String) : Int =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            logger.info(insertStmt)

            try {
                val stmt = it.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS)

                val start = System.currentTimeMillis()

                stmt.executeUpdate()

                logger.info("""$insertStmt 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())

                val keys = stmt.generatedKeys
                keys.next()

                keys.getInt(1)
            } catch (e: SQLException) {
                logger.severe("FAIL: $insertStmt")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun delete(url: String, table: String, id: Int) : Unit =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "DELETE FROM $table WHERE ID = $id"

            try {
                val start = System.currentTimeMillis()

                it.createStatement()
                    .execute(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }

fun deleteAll(url: String, table: String) : Unit =
    DriverManager.getConnection(url)
        .use {
            val logger: Logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

            val sql = "DELETE FROM $table"

            try {
                val start = System.currentTimeMillis()

                it.createStatement()
                    .execute(sql)

                logger.info("""$sql 
                    |   took ${System.currentTimeMillis() - start} msec""".trimMargin())
            } catch (e: SQLException) {
                logger.severe("FAIL: $sql")
                logger.severe { e.localizedMessage }
                throw e
            }
        }


fun String.urlFromResources(): String =
    "jdbc:sqlite:${
        object {}.javaClass.getResource(this)!!
            .toExternalForm()
    }"

fun String.urlFromWorkingDirectory(): String =
    "jdbc:sqlite:./src/main/resources$this"

fun String.asSql() = "'${this.replace("'", "''")}'"
fun LocalDate.asSql() = "'$this'"
fun Duration.asSql() = "'$this'"
