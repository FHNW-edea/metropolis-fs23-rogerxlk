package metropolis.xtracted.xtractedExplorer.model

data class Attribute<T : Any>(val caption    : String,
                              val value      : T,
                              val valueAsText: String  = "",
                              val isValid    : Boolean = true)
