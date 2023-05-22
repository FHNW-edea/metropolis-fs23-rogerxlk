package metropolis.repository

import metropolis.hello.data.City
import metropolis.xtracted.data.DbColumn
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.asSql


enum class CityColumn : DbColumn {
    ID,
    NAME,
    ASCII_NAME,
    ALTERNATE_NAMES,
    LATITUDE,
    LONGITUDE,
    FEATURE_CLASS,
    FEATURE_CODE,
    COUNTRY_CODE,
    CC2,
    ADMIN1_CODE,
    ADMIN2_CODE,
    ADMIN3_CODE,
    ADMIN4_CODE,
    POPULATION,
    ELEVATION,
    DEM,
    TIMEZONE,
    MODIFICATION_DATE
}

fun cityRepository(url: String) =
    CrudRepository(url         = url,
        table       = "CITY",
        idColumn    = CityColumn.ID,
        dataColumns = mapOf(
            CityColumn.NAME to { it.name.asSql() },
            CityColumn.ASCII_NAME to { it.asciiName?.asSql() },
            CityColumn.ALTERNATE_NAMES to { it.alternateNames?.asSql() },
            CityColumn.LATITUDE to { it.latitude.toString() },
            CityColumn.LONGITUDE to { it.longitude.toString() },
            CityColumn.FEATURE_CLASS to { it.featureClass.asSql() },
            CityColumn.FEATURE_CODE to { it.featureCode.asSql() },
            CityColumn.COUNTRY_CODE to { it.countryCode.asSql() },
            CityColumn.CC2 to { it.cc2?.asSql() },
            CityColumn.ADMIN1_CODE to { it.admin1Code?.asSql() },
            CityColumn.ADMIN2_CODE to { it.admin2Code?.asSql() },
            CityColumn.ADMIN3_CODE to { it.admin3Code?.asSql() },
            CityColumn.ADMIN4_CODE to { it.admin4Code?.asSql() },
            CityColumn.POPULATION to { it.population.toString() },
            CityColumn.ELEVATION to { it.elevation?.toString() },
            CityColumn.DEM to { it.dem.toString() },
            CityColumn.TIMEZONE to { it.timezone.asSql() },
            CityColumn.MODIFICATION_DATE to { it.modificationDate.toString() }
        ),

        mapper      = { City(id                = getInt   ("${CityColumn.ID}"),
            name              = getString("${CityColumn.NAME}"),
            asciiName         = getString("${CityColumn.ASCII_NAME}"),
            alternateNames    = getString("${CityColumn.ALTERNATE_NAMES}"),
            latitude          = getDouble("${CityColumn.LATITUDE}"),
            longitude         = getDouble("${CityColumn.LONGITUDE}"),
            featureClass      = getString("${CityColumn.FEATURE_CLASS}"),
            featureCode       = getString("${CityColumn.FEATURE_CODE}"),
            countryCode       = getString("${CityColumn.COUNTRY_CODE}"),
            cc2               = getString("${CityColumn.CC2}"),
            admin1Code        = getString("${CityColumn.ADMIN1_CODE}"),
            admin2Code        = getString("${CityColumn.ADMIN2_CODE}"),
            admin3Code        = getString("${CityColumn.ADMIN3_CODE}"),
            admin4Code        = getString("${CityColumn.ADMIN4_CODE}"),
            population        = getInt   ("${CityColumn.POPULATION}"),
            elevation         = getInt   ("${CityColumn.ELEVATION}"),
            dem               = getInt   ("${CityColumn.DEM}"),
            timezone          = getString("${CityColumn.TIMEZONE}"),
            modificationDate  = getDate  ("${CityColumn.MODIFICATION_DATE}")
        ) }
    )


/*
create table CITY
(
    ID                INTEGER          primary key  AUTOINCREMENT,
    NAME              VARCHAR(200)     NOT NULL,
    ASCII_NAME        VARCHAR(200),
    ALTERNATE_NAMES   VARCHAR(10000),
    LATITUDE          DOUBLE           NOT NULL,
    LONGITUDE         DOUBLE           NOT NULL,
    FEATURE_CLASS     CHAR             NOT NULL,
    FEATURE_CODE      VARCHAR(10)      NOT NULL,
    COUNTRY_CODE      VARCHAR(2)       NOT NULL,
    CC2               CHAR(200),
    ADMIN1_CODE       VARCHAR(20),
    ADMIN2_CODE       VARCHAR(80),
    ADMIN3_CODE       VARCHAR(20),
    ADMIN4_CODE       VARCHAR(20),
    POPULATION        INTEGER          NOT NULL,
    ELEVATION         INTEGER,
    DEM               INTEGER          NOT NULL,
    TIMEZONE          VARCHAR(40)      NOT NULL,
    MODIFICATION_DATE DATE             NOT NULL
);

create unique index CITY_ID
    on CITY (ID);

create index CITY_COUNTRY_CODE
    on CITY (COUNTRY_CODE);

create index CITY_NAME
    on CITY (NAME);

create index CITY_TIMEZONE
    on CITY (TIMEZONE);

 */