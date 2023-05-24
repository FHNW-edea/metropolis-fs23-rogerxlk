package metropolis.metropolistool.controller

import metropolis.data.City
import metropolis.data.Country
import metropolis.xtractedExplorer.model.TableState

data class MetropolisState(
    val cityState: TableState<City>,
    val countryState: TableState<Country>
)
