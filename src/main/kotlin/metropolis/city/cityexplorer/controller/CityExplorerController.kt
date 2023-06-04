package metropolis.city.cityexplorer.controller

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import metropolis.shared.data.City
import metropolis.shared.repository.CityColumnExplorer
import metropolis.shared.xtracted.controller.explorer.LazyTableController
import metropolis.shared.xtracted.model.editor.format
import metropolis.shared.xtracted.model.explorer.DoubleColumn
import metropolis.shared.xtracted.model.explorer.IntColumn
import metropolis.shared.xtracted.model.explorer.StringColumn
import metropolis.shared.xtracted.repository.explorer.LazyRepository
import java.time.LocalDate

private const val ELLIPSES = "..."

fun cityExplorerController(
    repository: LazyRepository<City>,
    onSelected: (Int) -> Unit,
    onCreate:   () -> Unit) =
    LazyTableController(title       = "Cities of the World",
        repository  = repository,
        onSelected  = onSelected,
        onCreate    = onCreate,
        defaultItem = City(
            -999, ELLIPSES, ELLIPSES, ELLIPSES, 0.0, 0.0, ELLIPSES, ELLIPSES, ELLIPSES,
            ELLIPSES, ELLIPSES, ELLIPSES, ELLIPSES, ELLIPSES, 0, 0, 0, ELLIPSES,
            LocalDate.now()
        ),
        columns     = listOf(
            StringColumn(header        = "Name",
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