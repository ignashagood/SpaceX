package nktns.spacex.ui.vehicles.vehicleList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nktns.spacex.base.calculateDiff
import nktns.spacex.data.VehiclesInteractor
import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.Ship
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.util.Result
import timber.log.Timber
import javax.inject.Inject

class VehicleListVM @Inject constructor(private val repository: VehiclesInteractor) : ViewModel() {

    private var _state = MutableStateFlow<VehicleListState>(VehicleListState.InitialLoading)
    private var _action = MutableSharedFlow<VehicleListAction>(extraBufferCapacity = 1)
    val state: StateFlow<VehicleListState> by ::_state
    val action: Flow<VehicleListAction> by ::_action

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getVehicles().collect { newVehicleList ->
                when (newVehicleList) {
                    is Result.Success -> {
                        val currentVehicleList: List<VehicleModel> =
                            (state.value as? VehicleListState.Content)?.vehicleList ?: emptyList()
                        val result: DiffUtil.DiffResult =
                            calculateDiff(
                                currentVehicleList,
                                newVehicleList.data,
                                VehicleModel::id
                            )
                        _state.value = VehicleListState.Content(newVehicleList.data, result)
                    }
                    is Result.Error -> {
                        Timber.e(newVehicleList.throwable)
                    }
                }
            }
        }
    }

    fun onItemClick(vehicle: VehicleModel) {
        when (vehicle) {
            is Rocket -> _action.tryEmit(VehicleListAction.ShowRocket(vehicle))
            is Ship -> _action.tryEmit(VehicleListAction.ShowShip(vehicle))
            is Dragon -> _action.tryEmit(VehicleListAction.ShowDragon(vehicle))
        }
    }
}
