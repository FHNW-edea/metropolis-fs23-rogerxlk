package metropolis.xtracted.xtractedEditor.repository

import metropolis.xtracted.xtractedEditor.data.DbColumnEditor
import java.sql.ResultSet

class CrudRepository<D: Identifiable>(private val url        : String,
                                      private val table      : String,
                                      private val idColumn   : DbColumnEditor,
                                      private val dataColumns: Map<DbColumnEditor, (D) -> String?>, //gibt an welcher Wert in die entsprechende DB-Column geschrieben werden soll
                                      private val mapper     : ResultSet.() -> D) {

    fun createKey(defaultValues: String = "DEFAULT VALUES") : Int =
        insertAndCreateKey(url        = url,
                           insertStmt = """INSERT INTO $table $defaultValues """.trimMargin())

    fun read(id: Int) : D?  =
        readFirst(url     = url,
                  table   = table,
                  columns = "$idColumn, " + dataColumns.keys.joinToString(),
                  where   = "$idColumn = $id",
                  map     = { mapper() })

    fun update(data: D){
        val valueUpdates = StringBuilder()
        dataColumns.entries.forEachIndexed { index, entry ->
            valueUpdates.append("${entry.key}")
            valueUpdates.append(" = ")
            valueUpdates.append(entry.value(data))
            if(index < dataColumns.size - 1){
                valueUpdates.append(", ")
            }

        }
        update(
            url = url,
            table = table,
            id = data.id,
            idColumn = idColumn,
            setStatement = """SET $valueUpdates """
        )

    }

    fun delete(id: Int) =
        delete(
            url = url,
            table = table,
            id = id
        )

}
