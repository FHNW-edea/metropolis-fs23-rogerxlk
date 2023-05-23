package metropolis.xtractedExplorer.repository

import java.sql.ResultSet
import metropolis.xtractedExplorer.data.DbColumnExplorer
import metropolis.xtractedExplorer.data.Filter
import metropolis.xtractedExplorer.data.SortDirective

class LazyRepository<T>(private val url        : String,
                        private val table      : String,
                        private val dataColumns: List<DbColumnExplorer>,
                        private val idColumn   : DbColumnExplorer,
                        private val mapper     : ResultSet.() -> T) {

    fun readFilteredIds(filters: List<Filter<*>>, sortDirective: SortDirective): List<Int> =
        readIds(url           = url,
                table         = table,
                idColumn      = idColumn,
                filters       = filters,
                sortDirective = sortDirective)

    fun read(id: Int) =
        readFirst(url     = url,
                  table   = table,
                  columns = dataColumns.joinToString(),
                  where   = "$idColumn = $id",
                  map     = { mapper() })

    fun totalCount() =
        count(url      = url,
              table    = table,
              idColumn = idColumn)

    fun filteredCount(filters: List<Filter<*>>) =
        count(url      = url,
              table    = table,
              idColumn = idColumn,
              filters  = filters)

}