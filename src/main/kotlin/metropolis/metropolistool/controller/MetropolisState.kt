package metropolis.metropolistool.controller

import metropolis.shareddata.City
import metropolis.shareddata.Country
import metropolis.xtractedEditor.controller.editor.EditorController
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableController
import metropolis.xtractedExplorer.model.TableState

data class MetropolisState(
//    val cityState: TableState<City>,
//    val countryState: TableState<Country>
    val title: String,
    val cityExplorerController: LazyTableController<City>,
    val cityEditorController: EditorController<City>?,
    val countryExplorerController: LazyTableController<Country>,
    val countryEditorController: EditorController<Country>?,
    val activeEditor: Editor? = null,
//    val activeController: Controller<*>,
//    val activeTable: TableState<*>
)

enum class Editor {
    CITY_EDITOR,
    COUNTRY_EDITOR
}
