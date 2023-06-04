package metropolis.shared.xtracted.repository.explorer

import metropolis.shared.xtracted.data.DbColumnExplorer
import metropolis.shared.xtracted.data.Filter
import metropolis.shared.xtracted.data.SortDirective
import metropolis.shared.xtracted.repository.count
import metropolis.shared.xtracted.repository.readFirst
import metropolis.shared.xtracted.repository.readIds
import java.sql.ResultSet

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