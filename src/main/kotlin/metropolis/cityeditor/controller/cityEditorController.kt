package metropolis.cityeditor.controller

import metropolis.shareddata.City
import metropolis.xtractedEditor.controller.editor.EditorController
import metropolis.xtractedEditor.model.*
import metropolis.xtractedEditor.repository.CrudRepository
import java.util.*
import metropolis.xtractedEditor.controller.editor.get

val ch = Locale("de", "CH")
fun cityEditorController(id: Int, repository: CrudRepository<City>): EditorController<City> {
    return EditorController(
        id = id,
        title = Message.TITLE,
        locale = ch,
        repository = repository,
        // diese Attribute von City werden in der DB abgespeichert, dazu muss eine Instanz von City aus den Attributen erzeugt werden
        // todo: die abzuspeichernden Werte muss mit der Attribut-Liste übereinstimmen
        asData = { attributes ->
            City(
                id = id,
                name = attributes[Id.NAME],
                asciiName = attributes[Id.ASCII_NAME],
                alternateNames = attributes[Id.ALTERNATE_NAMES],
                latitude = attributes[Id.LATITUDE],
                longitude = attributes[Id.LONGITUDE],
                featureClass = attributes[Id.FEATURE_CLASS],
                featureCode = attributes[Id.FEATURE_CODE],
                countryCode = attributes[Id.COUNTRY_CODE],
                cc2 = attributes[Id.CC2],
                admin1Code = attributes[Id.ADMIN1_CODE],
                admin2Code = attributes[Id.ADMIN2_CODE],
                admin3Code = attributes[Id.ADMIN3_CODE],
                admin4Code = attributes[Id.ADMIN4_CODE],
                population = attributes[Id.POPULATION],
                elevation = attributes[Id.ELEVATION],
            )
        },
        // diese Instanz von Mountain wurde aus der DB gelesen
        // todo: für alle Properties von Mountain, die im Editor erscheinen sollen, ein Attribute erzeugen
        asAttributeList = { city ->
            listOf(
                (stringAttribute(id = Id.NAME,
                    value = city.name,
                    required = true,
                    syntaxValidator = { (it.length <= 15).asValidationResult(Message.NAME_TOO_LONG) })),
                (stringAttribute(id = Id.COUNTRY_CODE,
                    value = city.countryCode,
                    required = true,
                    syntaxValidator = { (it.length <= 15).asValidationResult(Message.NAME_TOO_LONG) })),
            )
        }
    )
}


enum class Id(override val german: String, override val english: String) : AttributeId {
    NAME("Name", "Name"),
    ASCII_NAME("ASCII Name", "ASCII Name"),
    ALTERNATE_NAMES("Alternativnamen", "Alternate Names"),
    LATITUDE("Breitengrad", "Latitude"),
    LONGITUDE("Längengrad", "Longitude"),
    FEATURE_CLASS("Merkmal Klasse", "Feature Class"),
    FEATURE_CODE("Merkmal Code", "Feature Code"),
    COUNTRY_CODE("Ländercode", "Country Code"),
    CC2("CC2", "CC2"),
    ADMIN1_CODE("Admin1 Code", "Admin1 Code"),
    ADMIN2_CODE("Admin2 Code", "Admin2 Code"),
    ADMIN3_CODE("Admin3 Code", "Admin3 Code"),
    ADMIN4_CODE("Admin4 Code", "Admin4 Code"),
    POPULATION("Bevölkerung", "Population"),
    ELEVATION("Höhe", "Elevation"),
    DEM("DEM", "DEM"),
    TIMEZONE("Zeitzone", "Timezone"),
    MODIFICATION_DATE("Änderungsdatum", "Modification Date")
}

private enum class Message(override val german: String, override val english: String) : Translatable {
    TITLE("Stadt Editor", "City Editor"),
    TOO_HIGH("zu hoch", "too high"),
    TOO_LOW("zu niedrig", "too low"),
    NAME_TOO_LONG("Name zu lang", "name too long"),
    NOT_A_CANTON_LIST("keine Liste von Kantonen", "not a canton list")
}