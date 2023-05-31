package metropolis.countryexplorer.controller

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import metropolis.shareddata.Country
import metropolis.sharedrepository.CountryColumnExplorer
import metropolis.xtracted.xtractedEditor.model.format
import metropolis.xtracted.xtractedExplorer.controller.lazyloading.LazyTableController
import metropolis.xtracted.xtractedExplorer.model.DoubleColumn
import metropolis.xtracted.xtractedExplorer.model.IntColumn
import metropolis.xtracted.xtractedExplorer.model.StringColumn
import metropolis.xtracted.xtractedExplorer.repository.LazyRepository

private const val ELLIPSES = "..."

fun countryExplorerController(
    repository: LazyRepository<Country>,
    onSelected: (Int) -> Unit,
    onCreate:   () -> Unit) =
    LazyTableController(title       = "Countries of the World",
        repository  = repository,
        onSelected  = onSelected,
        onCreate    = onCreate,
        defaultItem = Country( -999, ELLIPSES,
            ELLIPSES,
            ELLIPSES, ELLIPSES, null, 0.0, 0, ELLIPSES, ELLIPSES, ELLIPSES, ELLIPSES, ELLIPSES,
            ELLIPSES,
            ELLIPSES,
            ELLIPSES,0,
            ELLIPSES,
            ELLIPSES
        ),
        columns     = listOf(StringColumn(header        = "Name",
            width         = 250.dp,
            alignment     = Alignment.CenterStart,
            fixed                 = true,
            dbColumnExplorer      = CountryColumnExplorer.NAME,
            valueProvider = { it.name }
        ),
       StringColumn(header        = "Capital",
                width         = 250.dp,
                alignment     = Alignment.CenterStart,
                fixed                 = true,
                dbColumnExplorer      = CountryColumnExplorer.CAPITAL,
                valueProvider = { it.capital }
            ),
            StringColumn(header        = "Continent",
                width         = 250.dp,
                alignment     = Alignment.CenterStart,
                fixed                 = true,
                dbColumnExplorer      = CountryColumnExplorer.CONTINENT,
                valueProvider = { it.continent }
            ),
            IntColumn(header        = "Population",
                width         = 250.dp,
                alignment     = Alignment.CenterStart,
                fixed                 = true,
                dbColumnExplorer      = CountryColumnExplorer.POPULATION,
                valueProvider = { it.population }
            ),
            DoubleColumn(header        = "Area in km2",
                width         = 120.dp,
                alignment     = Alignment.CenterEnd,
                fixed         = false,
                dbColumnExplorer      = CountryColumnExplorer.AREA_IN_SQKM,
                valueProvider = { it.areaInSqKm },
                formatter     = { it.format("%,.1f") }
            ),
            StringColumn(header        = "ISO Alpha2",
                width         = 200.dp,
                alignment     = Alignment.CenterStart,
                fixed         = false,
                dbColumnExplorer      = CountryColumnExplorer.ISO_ALPHA2,
                valueProvider = { it.isoAlpha2 }
            ),
            StringColumn(header        = "ISO Alpha3",
                width         = 200.dp,
                alignment     = Alignment.CenterStart,
                fixed         = false,
                dbColumnExplorer      = CountryColumnExplorer.ISO_ALPHA3,
                valueProvider = { it.isoAlpha3 }
            )
        )
    )