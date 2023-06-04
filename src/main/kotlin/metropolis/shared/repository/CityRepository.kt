package metropolis.shared.repository

import metropolis.shared.data.City
import metropolis.shared.xtracted.data.DbColumnEditor
import metropolis.shared.xtracted.data.DbColumnExplorer
import metropolis.shared.xtracted.repository.asSql
import metropolis.shared.xtracted.repository.editor.CrudRepository
import metropolis.shared.xtracted.repository.explorer.LazyRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


enum class CityColumnEditor : DbColumnEditor {
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

fun cityCrudRepository(url: String) =
    CrudRepository(url = url,
        table = "CITY",
        idColumn = CityColumnEditor.ID,
        dataColumns = mapOf(
            CityColumnEditor.NAME to { it.name.asSql() },
            CityColumnEditor.ASCII_NAME to { it.asciiName?.asSql() ?: "'NA'" },
            CityColumnEditor.ALTERNATE_NAMES to { it.alternateNames?.asSql() ?: "'NA'" },
            CityColumnEditor.LATITUDE to { it.latitude?.toString() ?: "0.0" },
            CityColumnEditor.LONGITUDE to { it.longitude?.toString() ?: "0.0" },
            CityColumnEditor.FEATURE_CLASS to { it.featureClass?.asSql() ?: "'NA'" },
            CityColumnEditor.FEATURE_CODE to { it.featureCode?.asSql() ?: "'NA'" },
            CityColumnEditor.COUNTRY_CODE to { it.countryCode?.asSql() ?: "'NA'" },
            CityColumnEditor.CC2 to { it.cc2?.asSql() ?: "'NA'" },
            CityColumnEditor.ADMIN1_CODE to { it.admin1Code?.asSql() ?: "'NA'" },
            CityColumnEditor.ADMIN2_CODE to { it.admin2Code?.asSql() ?: "'NA'" },
            CityColumnEditor.ADMIN3_CODE to { it.admin3Code?.asSql() ?: "'NA'" },
            CityColumnEditor.ADMIN4_CODE to { it.admin4Code?.asSql() ?: "'NA'" },
            CityColumnEditor.POPULATION to { it.population?.toString() ?: "0" },
            CityColumnEditor.ELEVATION to { it.elevation?.toString() ?: "0" },
            CityColumnEditor.DEM to { it.dem?.toString() ?: "0" },
            CityColumnEditor.TIMEZONE to { it.timezone?.asSql() ?: "'NA'" },
            CityColumnEditor.MODIFICATION_DATE to { it.modificationDate?.toString() ?: "'1970-01-01'" }
        ),
        mapper = {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val modificationDate: LocalDate =
                LocalDate.parse(getString("${CityColumnEditor.MODIFICATION_DATE}"), formatter)
            City(
                id = getInt("${CityColumnEditor.ID}"),
                name = getString("${CityColumnEditor.NAME}"),
                asciiName = getString("${CityColumnEditor.ASCII_NAME}"),
                alternateNames = getString("${CityColumnEditor.ALTERNATE_NAMES}"),
                latitude = getDouble("${CityColumnEditor.LATITUDE}"),
                longitude = getDouble("${CityColumnEditor.LONGITUDE}"),
                featureClass = getString("${CityColumnEditor.FEATURE_CLASS}"),
                featureCode = getString("${CityColumnEditor.FEATURE_CODE}"),
                countryCode = getString("${CityColumnEditor.COUNTRY_CODE}"),
                cc2 = getString("${CityColumnEditor.CC2}"),
                admin1Code = getString("${CityColumnEditor.ADMIN1_CODE}"),
                admin2Code = getString("${CityColumnEditor.ADMIN2_CODE}"),
                admin3Code = getString("${CityColumnEditor.ADMIN3_CODE}"),
                admin4Code = getString("${CityColumnEditor.ADMIN4_CODE}"),
                population = getInt("${CityColumnEditor.POPULATION}"),
                elevation = getInt("${CityColumnEditor.ELEVATION}"),
                dem = getInt("${CityColumnEditor.DEM}"),
                timezone = getString("${CityColumnEditor.TIMEZONE}"),
                modificationDate = modificationDate
            )
        }
    )


enum class CityColumnExplorer : DbColumnExplorer {
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

fun cityLazyRepository(url: String) =
    LazyRepository(
        url = url,
        table = "CITY",
        dataColumns = listOf(
            CityColumnExplorer.ID,
            CityColumnExplorer.NAME,
            CityColumnExplorer.ASCII_NAME,
            CityColumnExplorer.ALTERNATE_NAMES,
            CityColumnExplorer.LATITUDE,
            CityColumnExplorer.LONGITUDE,
            CityColumnExplorer.FEATURE_CLASS,
            CityColumnExplorer.FEATURE_CODE,
            CityColumnExplorer.COUNTRY_CODE,
            CityColumnExplorer.CC2,
            CityColumnExplorer.ADMIN1_CODE,
            CityColumnExplorer.ADMIN2_CODE,
            CityColumnExplorer.ADMIN3_CODE,
            CityColumnExplorer.ADMIN4_CODE,
            CityColumnExplorer.POPULATION,
            CityColumnExplorer.ELEVATION,
            CityColumnExplorer.DEM,
            CityColumnExplorer.TIMEZONE,
            CityColumnExplorer.MODIFICATION_DATE
        ),
        idColumn = CityColumnExplorer.ID,
        mapper = {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val modificationDate: LocalDate =
                LocalDate.parse(getString(CityColumnEditor.MODIFICATION_DATE.name), formatter)
            City(
                id = getInt(CityColumnEditor.ID.name),
                name = getString(CityColumnEditor.NAME.name),
                asciiName = getString(CityColumnEditor.ASCII_NAME.name),
                alternateNames = getString(CityColumnEditor.ALTERNATE_NAMES.name),
                latitude = getDouble(CityColumnEditor.LATITUDE.name),
                longitude = getDouble(CityColumnEditor.LONGITUDE.name),
                featureClass = getString(CityColumnEditor.FEATURE_CLASS.name),
                featureCode = getString(CityColumnEditor.FEATURE_CODE.name),
                countryCode = getString(CityColumnEditor.COUNTRY_CODE.name),
                cc2 = getString(CityColumnEditor.CC2.name),
                admin1Code = getString(CityColumnEditor.ADMIN1_CODE.name),
                admin2Code = getString(CityColumnEditor.ADMIN2_CODE.name),
                admin3Code = getString(CityColumnEditor.ADMIN3_CODE.name),
                admin4Code = getString(CityColumnEditor.ADMIN4_CODE.name),
                population = getInt(CityColumnEditor.POPULATION.name),
                elevation = getInt(CityColumnEditor.ELEVATION.name),
                dem = getInt(CityColumnEditor.DEM.name),
                timezone = getString(CityColumnEditor.TIMEZONE.name),
                modificationDate = modificationDate
            )
        }
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