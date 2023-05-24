package metropolis.metropolistool.controller
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AddCircle
//import androidx.compose.material.icons.filled.RemoveCircle
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.graphics.vector.ImageVector
//import metropolis.data.City
//import metropolis.data.Country
//import metropolis.xtractedEditor.repository.CrudRepository
//import metropolis.xtractedExplorer.controller.Action
//import metropolis.xtractedExplorer.controller.ControllerBase
//import metropolis.xtractedExplorer.controller.lazyloading.LazyTableAction
//import metropolis.xtractedExplorer.model.TableState
//
//class MetropolisController(
//    val cityRepository: CrudRepository<City>,
//    val countryRepository: CrudRepository<Country>,
//    val cityTrigger: (LazyTableAction) -> Unit,
//    val countryTrigger: (LazyTableAction) -> Unit
//) {
//
//    var state by mutableStateOf(
//        MetropolisState(
//            cityState = TableState(
//                title = "Cities",
//                dataProvider = { cityRepository.read(it) },
//                idProvider = { it.id }),
//            countryState = TableState(
//                title = "Countries",
//                dataProvider = { countryRepository.read(it) },
//                idProvider = { it.id })
//        )
//    )
//
//    fun triggerAction(action: Action) {
//        when (action) {
//            is MetropolisAction.CreateCity -> addCity()
//            is MetropolisAction.CreateCountry -> addCountry()
//            is MetropolisAction.DeleteCity -> deleteCity(action.cityId)
//            is MetropolisAction.DeleteCountry -> deleteCountry(action.countryId)
//            is MetropolisAction.UpdateCity -> updateCity(action.cityId)
//            is MetropolisAction.UpdateCountry -> updateCountry(action.countryId)
//            is MetropolisAction.SelectCity -> selectCity(action.cityId)
//            is MetropolisAction.SelectCountry -> selectCountry(action.countryId)
//            else -> throw IllegalStateException("Invalid action")
//        }
//    }
//
//    fun addCity() {
//        val newCity = City(id = cityRepository.createKey(), name = "New City")
//        cityTrigger(LazyTableAction.AddItem(newCity))
//        state = state.copy(cityState = state.cityState.copy(data = state.cityState.data + newCity))
//    }
//
//    fun addCountry() {
//        val newCountry = Country(
//            id = countryRepository.createKey(),
//            areaInSqKm = 0.0,
//            continent = "",
//            geonameId = 0,
//            isoAlpha2 = "",
//            isoAlpha3 = "",
//            name = "",
//            population = 0,
//        )
//        countryTrigger(LazyTableAction.AddItem(newCountry))
//        state = state.copy(countryState = state.countryState.copy(data = state.countryState.data + newCountry))
//    }
//
//    fun selectCity(cityId: Int): City? {
//        return cityRepository.read(cityId)
//    }
//
//    fun selectCountry(countryId: Int): Country? {
//        return countryRepository.read(countryId)
//    }
//
//    fun deleteCity(cityId: Int) {
//        val cityToDelete = cityRepository.read(cityId)
//        cityRepository.delete(cityId)
//        cityTrigger(LazyTableAction.RemoveItem(cityToDelete))
//    }
//
//    fun deleteCountry(countryId: Int) {
//        val countryToDelete = countryRepository.read(countryId)
//        countryRepository.delete(countryId)
//        countryTrigger(LazyTableAction.RemoveItem(countryToDelete))
//    }
//
//    fun updateCity(cityId: Int) {
//        val city = cityRepository.read(cityId)
//        if (city != null) {
//            val updatedCity = city.copy(name = "Updated City")
//            cityRepository.update(updatedCity)
//            cityTrigger(LazyTableAction.UpdateItem(updatedCity))
//        }
//    }
//
//    fun updateCountry(countryId: Int) {
//        val country = countryRepository.read(countryId)
//        if (country != null) {
//            val updatedCountry = country.copy(name = "Updated Country")
//            countryRepository.update(updatedCountry)
//            countryTrigger(LazyTableAction.UpdateItem(updatedCountry))
//        }
//    }
//}
//
//sealed class MetropolisAction(
//    override val name: String,
//    override val icon: ImageVector? = null,
//    override val enabled: Boolean
//) : Action {
//    class CreateCity : MetropolisAction("New City", Icons.Filled.AddCircle, true)
//    class CreateCountry : MetropolisAction("New Country", Icons.Filled.AddCircle, true)
//    class UpdateCity(val cityId: Int) : MetropolisAction("Update City", null, true)
//    class UpdateCountry(val countryId: Int) : MetropolisAction("Update Country", null, true)
//    class DeleteCity(val cityId: Int) : MetropolisAction("Delete City", Icons.Filled.RemoveCircle, true)
//    class DeleteCountry(val countryId: Int) : MetropolisAction("Delete Country", Icons.Filled.RemoveCircle, true)
//    class SelectCity(val cityId: Int) : MetropolisAction("Select City", null, true)
//    class SelectCountry(val countryId: Int) : MetropolisAction("Select Country", null, true)
//}
