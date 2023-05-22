package metropolis.repository

import metropolis.hello.data.Country
import metropolis.xtracted.data.DbColumn
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.asSql

enum class CountryColumn : DbColumn {
    ISO_ALPHA2,
    ISO_ALPHA3,
    ISO_NUMERIC,
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

fun countryRepository(url: String) =
    CrudRepository(url         = url,
        table       = "COUNTRY",
        idColumn    = CountryColumn.ISO_NUMERIC,
        dataColumns = mapOf(
            CountryColumn.ISO_ALPHA2 to { it.isoAlpha2.asSql() },
            CountryColumn.ISO_ALPHA3 to { it.isoAlpha3.asSql() },
            CountryColumn.FIPS_CODE to { it.fipsCode?.asSql() },
            CountryColumn.NAME to { it.name.asSql() },
            CountryColumn.CAPITAL to { it.capital?.asSql() },
            CountryColumn.AREA_IN_SQKM to { it.areaInSqKm.toString() },
            CountryColumn.POPULATION to { it.population.toString() },
            CountryColumn.CONTINENT to { it.continent.asSql() },
            CountryColumn.TLD to { it.tld?.asSql() },
            CountryColumn.CURRENCY_CODE to { it.currencyCode?.asSql() },
            CountryColumn.CURRENCY_NAME to { it.currencyName?.asSql() },
            CountryColumn.PHONE to { it.phone?.asSql() },
            CountryColumn.POSTAL_CODE_FORMAT to { it.postalCodeFormat?.asSql() },
            CountryColumn.POSTAL_CODE_REGEX to { it.postalCodeRegex?.asSql() },
            CountryColumn.LANGUAGES to { it.languages?.asSql() },
            CountryColumn.GEONAME_ID to { it.geonameId.toString() },
            CountryColumn.NEIGHBOURS to { it.neighbours?.asSql() },
            CountryColumn.EQUIVALENT_FIPS_CODE to { it.equivalentFipsCode?.asSql() }
        ),

        mapper      = { Country(isoAlpha2           = getString("${CountryColumn.ISO_ALPHA2}"),
            isoAlpha3           = getString("${CountryColumn.ISO_ALPHA3}"),
            id                  = getInt   ("${CountryColumn.ISO_NUMERIC}"),
            fipsCode            = getString("${CountryColumn.FIPS_CODE}"),
            name                = getString("${CountryColumn.NAME}"),
            capital             = getString("${CountryColumn.CAPITAL}"),
            areaInSqKm          = getDouble("${CountryColumn.AREA_IN_SQKM}"),
            population          = getInt   ("${CountryColumn.POPULATION}"),
            continent           = getString("${CountryColumn.CONTINENT}"),
            tld                 = getString("${CountryColumn.TLD}"),
            currencyCode        = getString("${CountryColumn.CURRENCY_CODE}"),
            currencyName        = getString("${CountryColumn.CURRENCY_NAME}"),
            phone               = getString("${CountryColumn.PHONE}"),
            postalCodeFormat    = getString("${CountryColumn.POSTAL_CODE_FORMAT}"),
            postalCodeRegex     = getString("${CountryColumn.POSTAL_CODE_REGEX}"),
            languages           = getString("${CountryColumn.LANGUAGES}"),
            geonameId           = getLong  ("${CountryColumn.GEONAME_ID}"),
            neighbours          = getString("${CountryColumn.NEIGHBOURS}"),
            equivalentFipsCode  = getString("${CountryColumn.EQUIVALENT_FIPS_CODE}")
        ) }
    )

/*
CREATE TABLE COUNTRY (
                         ISO_ALPHA2           CHAR(2)          NOT NULL,
                         ISO_ALPHA3           CHAR(3)          NOT NULL,
                         ISO_NUMERIC          INTEGER          PRIMARY KEY AUTOINCREMENT,
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