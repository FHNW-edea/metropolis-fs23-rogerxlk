package metropolis.shared.xtracted.model.explorer

data class Attribute<T : Any>(val caption    : String,
                              val value      : T,
                              val valueAsText: String  = "",
                              val isValid    : Boolean = true)
