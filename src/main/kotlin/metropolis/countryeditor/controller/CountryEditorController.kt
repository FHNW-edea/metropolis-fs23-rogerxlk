package metropolis.countryeditor.controller

import metropolis.shareddata.Country
import metropolis.xtracted.xtractedEditor.controller.editor.EditorController
import metropolis.xtracted.xtractedEditor.controller.editor.get
import metropolis.xtracted.xtractedEditor.model.*
import java.util.*
import metropolis.xtracted.xtractedEditor.repository.CrudRepository

val ch = Locale("de", "CH")
fun countryEditorController(
    id: Int,
    repository: CrudRepository<Country>,
    onSave: () -> Unit,
    onDelete: () -> Unit
) = EditorController(
        id = id,
        title = Message.TITLE,
        locale = ch,
        onSave = onSave,
        onDelete = onDelete,
        repository = repository,
        asData = { attributes ->
            Country(
                id = id,
                isoAlpha2 = attributes[Id.ISO_ALPHA2],
                isoAlpha3 = attributes[Id.ISO_ALPHA3],
//                fipsCode = attributes[Id.FIPS_CODE],
                name = attributes[Id.NAME],
                capital = attributes[Id.CAPITAL],
                areaInSqKm = attributes[Id.AREA_IN_SQ_KM],
                population = attributes[Id.POPULATION],
                continent = attributes[Id.CONTINENT],
//                tld = attributes[Id.TLD],
//                currencyCode = attributes[Id.CURRENCY_CODE],
//                currencyName = attributes[Id.CURRENCY_NAME],
//                phone = attributes[Id.PHONE],
//                postalCodeFormat = attributes[Id.POSTAL_CODE_FORMAT],
//                postalCodeRegex = attributes[Id.POSTAL_CODE_REGEX],
//                languages = attributes[Id.LANGUAGES],
                geonameId = attributes[Id.GEONAME_ID],
//                neighbours = attributes[Id.NEIGHBOURS],
//                equivalentFipsCode = attributes[Id.EQUIVALENT_FIPS_CODE]
            )
        },
        asAttributeList = { country ->
            listOf(
                (stringAttribute(id = Id.NAME,
                    value = country.name,
                    required = true,
                    syntaxValidator = { (it.length <= 15).asValidationResult(Message.NAME_TOO_LONG) })),
                (stringAttribute(id = Id.CAPITAL,
                    value = country.capital,
                    required = true,
                    syntaxValidator = { (it.length <= 15).asValidationResult(Message.NAME_TOO_LONG) })),
                (stringAttribute(id = Id.CONTINENT,
                    value = country.continent,
                    required = true,
                    syntaxValidator = { (it.length <= 15).asValidationResult(Message.NAME_TOO_LONG) })),
                (integerAttribute(id = Id.POPULATION,
                    value = country.population,
                    required = true)),
                doubleAttribute(id = Id.AREA_IN_SQ_KM,
                    value = country.areaInSqKm,
                    required = true),
                (stringAttribute(id = Id.ISO_ALPHA2,
                    value = country.isoAlpha2,
                    required = true,
                    syntaxValidator = { (it.length == 2).asValidationResult(Message.TOO_HIGH) })),
                (stringAttribute(id = Id.ISO_ALPHA3,
                    value = country.isoAlpha3,
                    required = true,
                    syntaxValidator = { (it.length == 3).asValidationResult(Message.TOO_HIGH) })),
                (longAttribute(id = Id.GEONAME_ID,
                    value = country.geonameId,
                    required = true))
            )
        }
    )


enum class Id(override val german: String, override val english: String) : AttributeId {
    ISO_ALPHA2("ISO Alpha2", "ISO Alpha2"),
    ISO_ALPHA3("ISO Alpha3", "ISO Alpha3"),
//    FIPS_CODE("FIPS-Code", "FIPS Code"),
    NAME("Name", "Name"),
    CAPITAL("Hauptstadt", "Capital"),
    AREA_IN_SQ_KM("Fläche in km²", "Area in sq km"),
    POPULATION("Bevölkerung", "Population"),
    CONTINENT("Kontinent", "Continent"),
//    TLD("Top-Level-Domain", "TLD"),
//    CURRENCY_CODE("Währungscode", "Currency Code"),
//    CURRENCY_NAME("Währungsname", "Currency Name"),
//    PHONE("Telefon", "Phone"),
//    POSTAL_CODE_FORMAT("Postleitzahlenformat", "Postal Code Format"),
//    POSTAL_CODE_REGEX("Postleitzahlen Regex", "Postal Code Regex"),
//    LANGUAGES("Sprachen", "Languages"),
    GEONAME_ID("Geoname-ID", "Geoname ID"),
//    NEIGHBOURS("Nachbarländer", "Neighbours"),
//    EQUIVALENT_FIPS_CODE("Äquivalenter FIPS-Code", "Equivalent FIPS Code")
}

private enum class Message(override val german: String, override val english: String) : Translatable {
    TITLE("Land Editor", "Country Editor"),
    TOO_HIGH("zu hoch", "too high"),
    TOO_LOW("zu niedrig", "too low"),
    NAME_TOO_LONG("Name zu lang", "name too long"),
    NOT_A_CANTON_LIST("keine Liste von Kantonen", "not a canton list")
}