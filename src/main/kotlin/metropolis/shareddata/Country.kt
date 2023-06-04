package metropolis.shareddata

import metropolis.xtracted.xtractedEditor.repository.Identifiable


data class Country(
    override val id  : Int,
    val isoAlpha2: String,
    val isoAlpha3: String,
    val fipsCode: String? = null,
    val name: String,
    val capital: String? = null,
    val areaInSqKm: Double,
    val population: Int,
    val continent: String,
    val tld: String? = null,
    val currencyCode: String? = null,
    val currencyName: String? = null,
    val phone: String? = null,
    val postalCodeFormat: String? = null,
    val postalCodeRegex: String? = null,
    val languages: String? = null,
    val geonameId: Long? = null,
    val neighbours: String? = null,
    val equivalentFipsCode: String? = null
) : Identifiable
