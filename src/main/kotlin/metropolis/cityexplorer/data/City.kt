package metropolis.cityexplorer.data

import metropolis.xtractedEditor.repository.Identifiable
import java.time.LocalDate

data class City(
    override val id: Int,
    val name: String,
    val asciiName: String? = null,
    val alternateNames: String? = null,
    val latitude: Double,
    val longitude: Double,
    val featureClass: String,
    val featureCode: String,
    val countryCode: String,
    val cc2: String? = null,
    val admin1Code: String? = null,
    val admin2Code: String? = null,
    val admin3Code: String? = null,
    val admin4Code: String? = null,
    val population: Int,
    val elevation: Int? = null,
    val dem: Int,
    val timezone: String,
    val modificationDate: LocalDate
) : Identifiable
