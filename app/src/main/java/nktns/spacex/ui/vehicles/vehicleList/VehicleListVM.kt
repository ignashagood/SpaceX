package nktns.spacex.ui.vehicles.vehicleList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import nktns.spacex.base.calculateDiff
import nktns.spacex.data.VehiclesInteractor
import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.Ship
import nktns.spacex.data.database.VehicleModel
import timber.log.Timber
import javax.inject.Inject

class VehicleListVM @Inject constructor(private val repository: VehiclesInteractor) : ViewModel() {

    private var _state = MutableLiveData<VehicleListState>(VehicleListState.InitialLoading)
    private var _action = MutableSharedFlow<VehicleListAction>(extraBufferCapacity = 1)
    val state: LiveData<VehicleListState> by ::_state
    val action: Flow<VehicleListAction> by ::_action

    init {
        repository.getVehicles().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { newVehicleList ->
                    val currentVehicleList: List<VehicleModel> =
                        (state.value as? VehicleListState.Content)?.vehicleList ?: emptyList()
                    val result: DiffUtil.DiffResult =
                        calculateDiff(
                            currentVehicleList,
                            newVehicleList,
                            VehicleModel::id
                        )
                    _state.value = VehicleListState.Content(newVehicleList, result)
                },
                {
                    Timber.e(it)
                }
            )
    }

    fun onItemClick(vehicle: VehicleModel) {
        when (vehicle) {
            is Rocket -> _action.tryEmit(VehicleListAction.ShowRocket(vehicle))
            is Ship -> _action.tryEmit(VehicleListAction.ShowShip(vehicle))
            is Dragon -> _action.tryEmit(VehicleListAction.ShowDragon(vehicle))
        }
    }
}
