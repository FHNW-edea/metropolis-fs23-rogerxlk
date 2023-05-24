package metropolis.countryeditor.controller

import metropolis.data.Country
import metropolis.xtractedEditor.controller.editor.EditorController
import metropolis.xtractedEditor.model.*
import metropolis.xtractedEditor.repository.CrudRepository
import java.util.*
import metropolis.xtractedEditor.controller.editor.get

val ch = Locale("de", "CH")
fun countryEditor(id: Int, repository: CrudRepository<Country>): EditorController<Country> {
    return EditorController(
        id = id,
        title = Message.TITLE,
        locale = ch,
        repository = repository,
        asData = { attributes ->
            Country(
                id = id,
                isoAlpha2 = attributes[Id.ISO_ALPHA2],
                isoAlpha3 = attributes[Id.ISO_ALPHA3],
                fipsCode = attributes[Id.FIPS_CODE],
                name = attributes[Id.NAME],
                capital = attributes[Id.CAPITAL],
                areaInSqKm = attributes[Id.AREA_IN_SQ_KM],
                population = attributes[Id.POPULATION],
                continent = attributes[Id.CONTINENT],
                tld = attributes[Id.TLD],
                currencyCode = attributes[Id.CURRENCY_CODE],
                currencyName = attributes[Id.CURRENCY_NAME],
                phone = attributes[Id.PHONE],
                postalCodeFormat = attributes[Id.POSTAL_CODE_FORMAT],
                postalCodeRegex = attributes[Id.POSTAL_CODE_REGEX],
                languages = attributes[Id.LANGUAGES],
                geonameId = attributes[Id.GEONAME_ID],
                neighbours = attributes[Id.NEIGHBOURS],
                equivalentFipsCode = attributes[Id.EQUIVALENT_FIPS_CODE]
            )
        },
        asAttributeList = { country ->
            listOf(
                (stringAttribute(id = Id.NAME,
                    value = country.name,
                    required = true,
                    syntaxValidator = { (it.length <= 15).asValidationResult(Message.NAME_TOO_LONG) })),
            )
        }
    )
}


enum class Id(override val german: String, override val english: String) : AttributeId {
    ISO_ALPHA2("ISO Alpha2", "ISO Alpha2"),
    ISO_ALPHA3("ISO Alpha3", "ISO Alpha3"),
    FIPS_CODE("FIPS-Code", "FIPS Code"),
    NAME("Name", "Name"),
    CAPITAL("Hauptstadt", "Capital"),
    AREA_IN_SQ_KM("Fläche in km²", "Area in sq km"),
    POPULATION("Bevölkerung", "Population"),
    CONTINENT("Kontinent", "Continent"),
    TLD("Top-Level-Domain", "TLD"),
    CURRENCY_CODE("Währungscode", "Currency Code"),
    CURRENCY_NAME("Währungsname", "Currency Name"),
    PHONE("Telefon", "Phone"),
    POSTAL_CODE_FORMAT("Postleitzahlenformat", "Postal Code Format"),
    POSTAL_CODE_REGEX("Postleitzahlen Regex", "Postal Code Regex"),
    LANGUAGES("Sprachen", "Languages"),
    GEONAME_ID("Geoname-ID", "Geoname ID"),
    NEIGHBOURS("Nachbarländer", "Neighbours"),
    EQUIVALENT_FIPS_CODE("Äquivalenter FIPS-Code", "Equivalent FIPS Code")
}

private enum class Message(override val german: String, override val english: String) : Translatable {
    TITLE("Land Editor", "Country Editor"),
    TOO_HIGH("zu hoch", "too high"),
    TOO_LOW("zu niedrig", "too low"),
    NAME_TOO_LONG("Name zu lang", "name too long"),
    NOT_A_CANTON_LIST("keine Liste von Kantonen", "not a canton list")
}