package metropolis.metropolistool.controller

import metropolis.shareddata.City
import metropolis.shareddata.Country
import metropolis.xtractedExplorer.model.TableState

data class MetropolisState(
    val cityState: TableState<City>,
    val countryState: TableState<Country>
)
