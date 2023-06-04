package metropolis.shared.repository

import metropolis.shared.data.Country
import metropolis.shared.xtracted.data.DbColumnEditor
import metropolis.shared.xtracted.data.DbColumnExplorer
import metropolis.shared.xtracted.repository.asSql
import metropolis.shared.xtracted.repository.editor.CrudRepository
import metropolis.shared.xtracted.repository.explorer.LazyRepository


enum class CountryColumnEditor : DbColumnEditor {
    ISO_ALPHA2,
    ISO_ALPHA3,
    ID,
    FIPS_CODE,
    NAME,
    CAPITAL,
    AREA_IN_SQKM,
    POPULATION,
    CONTINENT,
    TLD,
    CURRENCY_CODE,
    CURRENCY_NAME,
    PHONE,
    POSTAL_CODE_FORMAT,
    POSTAL_CODE_REGEX,
    LANGUAGES,
    GEONAME_ID,
    NEIGHBOURS,
    EQUIVALENT_FIPS_CODE
}

fun countryCrudRepository(url: String) =
    CrudRepository(url         = url,
        table       = "COUNTRY",
        idColumn    = CountryColumnEditor.ID,
        dataColumns = mapOf(
            CountryColumnEditor.ISO_ALPHA2 to { it.isoAlpha2.asSql() },
            CountryColumnEditor.ISO_ALPHA3 to { it.isoAlpha3.asSql() },
            CountryColumnEditor.FIPS_CODE to { it.fipsCode?.asSql() },
            CountryColumnEditor.NAME to { it.name.asSql() },
            CountryColumnEditor.CAPITAL to { it.capital?.asSql() },
            CountryColumnEditor.AREA_IN_SQKM to { it.areaInSqKm.toString() },
            CountryColumnEditor.POPULATION to { it.population.toString() },
            CountryColumnEditor.CONTINENT to { it.continent.asSql() },
            CountryColumnEditor.TLD to { it.tld?.asSql() },
            CountryColumnEditor.CURRENCY_CODE to { it.currencyCode?.asSql() },
            CountryColumnEditor.CURRENCY_NAME to { it.currencyName?.asSql() },
            CountryColumnEditor.PHONE to { it.phone?.asSql() },
            CountryColumnEditor.POSTAL_CODE_FORMAT to { it.postalCodeFormat?.asSql() },
            CountryColumnEditor.POSTAL_CODE_REGEX to { it.postalCodeRegex?.asSql() },
            CountryColumnEditor.LANGUAGES to { it.languages?.asSql() },
            CountryColumnEditor.GEONAME_ID to { it.geonameId.toString() },
            CountryColumnEditor.NEIGHBOURS to { it.neighbours?.asSql() },
            CountryColumnEditor.EQUIVALENT_FIPS_CODE to { it.equivalentFipsCode?.asSql() }
        ),

        mapper      = {
            Country(
                isoAlpha2 = getString("${CountryColumnEditor.ISO_ALPHA2}"),
                isoAlpha3 = getString("${CountryColumnEditor.ISO_ALPHA3}"),
                id = getInt("${CountryColumnEditor.ID}"),
                fipsCode = getString("${CountryColumnEditor.FIPS_CODE}"),
                name = getString("${CountryColumnEditor.NAME}"),
                capital = getString("${CountryColumnEditor.CAPITAL}"),
                areaInSqKm = getDouble("${CountryColumnEditor.AREA_IN_SQKM}"),
                population = getInt("${CountryColumnEditor.POPULATION}"),
                continent = getString("${CountryColumnEditor.CONTINENT}"),
                tld = getString("${CountryColumnEditor.TLD}"),
                currencyCode = getString("${CountryColumnEditor.CURRENCY_CODE}"),
                currencyName = getString("${CountryColumnEditor.CURRENCY_NAME}"),
                phone = getString("${CountryColumnEditor.PHONE}"),
                postalCodeFormat = getString("${CountryColumnEditor.POSTAL_CODE_FORMAT}"),
                postalCodeRegex = getString("${CountryColumnEditor.POSTAL_CODE_REGEX}"),
                languages = getString("${CountryColumnEditor.LANGUAGES}"),
                geonameId = getLong("${CountryColumnEditor.GEONAME_ID}"),
                neighbours = getString("${CountryColumnEditor.NEIGHBOURS}"),
                equivalentFipsCode = getString("${CountryColumnEditor.EQUIVALENT_FIPS_CODE}")
            )
        }
    )

enum class CountryColumnExplorer : DbColumnExplorer {
    ISO_ALPHA2,
    ISO_ALPHA3,
    ID,
    FIPS_CODE,
    NAME,
    CAPITAL,
    AREA_IN_SQKM,
    POPULATION,
    CONTINENT,
    TLD,
    CURRENCY_CODE,
    CURRENCY_NAME,
    PHONE,
    POSTAL_CODE_FORMAT,
    POSTAL_CODE_REGEX,
    LANGUAGES,
    GEONAME_ID,
    NEIGHBOURS,
    EQUIVALENT_FIPS_CODE
}

fun countryLazyRepository(url: String) =
    LazyRepository(
        url = url,
        table = "COUNTRY",
        dataColumns = listOf(
            CountryColumnExplorer.ISO_ALPHA2,
            CountryColumnExplorer.ISO_ALPHA3,
            CountryColumnExplorer.ID,
            CountryColumnExplorer.FIPS_CODE,
            CountryColumnExplorer.NAME,
            CountryColumnExplorer.CAPITAL,
            CountryColumnExplorer.AREA_IN_SQKM,
            CountryColumnExplorer.POPULATION,
            CountryColumnExplorer.CONTINENT,
            CountryColumnExplorer.TLD,
            CountryColumnExplorer.CURRENCY_CODE,
            CountryColumnExplorer.CURRENCY_NAME,
            CountryColumnExplorer.PHONE,
            CountryColumnExplorer.POSTAL_CODE_FORMAT,
            CountryColumnExplorer.POSTAL_CODE_REGEX,
            CountryColumnExplorer.LANGUAGES,
            CountryColumnExplorer.GEONAME_ID,
            CountryColumnExplorer.NEIGHBOURS,
            CountryColumnExplorer.EQUIVALENT_FIPS_CODE
        ),
        idColumn = CountryColumnExplorer.ID,
        mapper = {
            Country(
                isoAlpha2 = getString(CountryColumnExplorer.ISO_ALPHA2.name),
                isoAlpha3 = getString(CountryColumnExplorer.ISO_ALPHA3.name),
                id = getInt(CountryColumnExplorer.ID.name),
                fipsCode = getString(CountryColumnExplorer.FIPS_CODE.name),
                name = getString(CountryColumnExplorer.NAME.name),
                capital = getString(CountryColumnExplorer.CAPITAL.name),
                areaInSqKm = getDouble(CountryColumnExplorer.AREA_IN_SQKM.name),
                population = getInt(CountryColumnExplorer.POPULATION.name),
                continent = getString(CountryColumnExplorer.CONTINENT.name),
                tld = getString(CountryColumnExplorer.TLD.name),
                currencyCode = getString(CountryColumnExplorer.CURRENCY_CODE.name),
                currencyName = getString(CountryColumnExplorer.CURRENCY_NAME.name),
                phone = getString(CountryColumnExplorer.PHONE.name),
                postalCodeFormat = getString(CountryColumnExplorer.POSTAL_CODE_FORMAT.name),
                postalCodeRegex = getString(CountryColumnExplorer.POSTAL_CODE_REGEX.name),
                languages = getString(CountryColumnExplorer.LANGUAGES.name),
                geonameId = getLong(CountryColumnExplorer.GEONAME_ID.name),
                neighbours = getString(CountryColumnExplorer.NEIGHBOURS.name),
                equivalentFipsCode = getString(CountryColumnExplorer.EQUIVALENT_FIPS_CODE.name)
            )
        }
    )


/*
CREATE TABLE COUNTRY (
                         ISO_ALPHA2           CHAR(2)          NOT NULL,
                         ISO_ALPHA3           CHAR(3)          NOT NULL,
                         ID          INTEGER          PRIMARY KEY AUTOINCREMENT,
                         FIPS_CODE            VARCHAR(3),
                         NAME                 VARCHAR(200)     NOT NULL,
                         CAPITAL              VARCHAR(200),
                         AREA_IN_SQKM         DOUBLE PRECISION NOT NULL,
                         POPULATION           INTEGER          NOT NULL,
                         CONTINENT            CHAR(2)          NOT NULL,
                         TLD                  CHAR(3),
                         CURRENCY_CODE        CHAR(3),
                         CURRENCY_NAME        VARCHAR(20),
                         PHONE                VARCHAR(10),
                         POSTAL_CODE_FORMAT   VARCHAR(10),
                         POSTAL_CODE_REGEX    VARCHAR(30),
                         LANGUAGES            VARCHAR(30),
                         GEONAME_ID           BIGINT           NOT NULL,
                         NEIGHBOURS           VARCHAR(30),
                         EQUIVALENT_FIPS_CODE VARCHAR(3)
);

 */