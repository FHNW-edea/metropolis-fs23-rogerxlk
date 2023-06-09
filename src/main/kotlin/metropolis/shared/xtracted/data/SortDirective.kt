package metropolis.shared.xtracted.data


data class SortDirective(val column: DbColumnExplorer?, val direction: SortDirection = SortDirection.ASC)

enum class SortDirection {
    ASC, DESC
}

val UNORDERED = SortDirective(null, SortDirection.ASC)