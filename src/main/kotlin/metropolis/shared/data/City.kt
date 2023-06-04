package metropolis.shared.data

import metropolis.shared.xtracted.repository.editor.Identifiable
import java.time.LocalDate

data class City(
    override val id: Int,
    val name: String,
    val asciiName: String? = null,
    val alternateNames: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val featureClass: String? = null,
    val featureCode: String? = null,
    val countryCode: String? = null,
    val cc2: String? = null,
    val admin1Code: String? = null,
    val admin2Code: String? = null,
    val admin3Code: String? = null,
    val admin4Code: String? = null,
    val population: Int? = null,
    val elevation: Int? = null,
    val dem: Int?   = null,
    val timezone: String? = null,
    val modificationDate: LocalDate? = null
) : Identifiable
