package metropolis.cityexplorer.controller

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import metropolis.data.City
import metropolis.repository.CityColumnExplorer
import metropolis.xtractedEditor.model.format
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableController
import metropolis.xtractedExplorer.model.DoubleColumn
import metropolis.xtractedExplorer.model.IntColumn
import metropolis.xtractedExplorer.model.StringColumn
import metropolis.xtractedExplorer.repository.LazyRepository
import java.time.LocalDate

private const val ELLIPSES = "..."

fun cityExplorerController(repository: LazyRepository<City>) =
    LazyTableController(title       = "Cities of the World",
        repository  = repository,
        defaultItem = City( -999, ELLIPSES,ELLIPSES,ELLIPSES, 0.0, 0.0, ELLIPSES, ELLIPSES, ELLIPSES,
            ELLIPSES, ELLIPSES, ELLIPSES, ELLIPSES,ELLIPSES,0,0,0,ELLIPSES,
            LocalDate.now()
        ),
        columns     = listOf(StringColumn(header        = "Name",
            width         = 250.dp,
            alignment     = Alignment.CenterStart,
            fixed                 = true,
            dbColumnExplorer      = CityColumnExplorer.NAME,
            valueProvider = { it.name }
        ),
       StringColumn(header        = "ASCII Name",
                width         = 250.dp,
                alignment     = Alignment.CenterStart,
                fixed                 = true,
                dbColumnExplorer      = CityColumnExplorer.ASCII_NAME,
                valueProvider = { it.asciiName }
            ),
            StringColumn(header        = "Country Code",
                width         = 250.dp,
                alignment     = Alignment.CenterStart,
                fixed                 = true,
                dbColumnExplorer      = CityColumnExplorer.COUNTRY_CODE,
                valueProvider = { it.countryCode }
            ),
            IntColumn(header        = "Population",
                width         = 250.dp,
                alignment     = Alignment.CenterStart,
                fixed                 = true,
                dbColumnExplorer      = CityColumnExplorer.POPULATION,
                valueProvider = { it.population }
            ),
            DoubleColumn(header        = "Latitude",
                width         = 120.dp,
                alignment     = Alignment.CenterEnd,
                fixed         = false,
                dbColumnExplorer      = CityColumnExplorer.LATITUDE,
                valueProvider = { it.latitude },
                formatter     = { it.format("%,.1f") }
            ),
            DoubleColumn(header        = "Longitude",
                width         = 200.dp,
                alignment     = Alignment.CenterStart,
                fixed         = false,
                dbColumnExplorer      = CityColumnExplorer.LONGITUDE,
                valueProvider = { it.longitude },
                formatter     = { it.format("%,.1f") }
            ),
        )
    )