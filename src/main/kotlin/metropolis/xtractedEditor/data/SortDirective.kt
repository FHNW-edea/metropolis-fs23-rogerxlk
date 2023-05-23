package metropolis.xtractedEditor.data


data class SortDirective(val column: DbColumnEditor?, val direction: SortDirection = SortDirection.ASC)

enum class SortDirection {
    ASC, DESC
}

val UNORDERED = SortDirective(null, SortDirection.ASC)