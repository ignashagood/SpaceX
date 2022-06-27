package nktns.spacex.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nktns.spacex.base.calculateDiff
import nktns.spacex.data.RocketListItem
import nktns.spacex.data.SpaceXRepository
import javax.inject.Inject

class VehiclesVM @Inject constructor(private val repository: SpaceXRepository) : ViewModel() {

    private var _state = MutableStateFlow<VehiclesState>(VehiclesState.InitialLoading)
    val state: StateFlow<VehiclesState> by ::_state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAllRockets().collect { newRocketList ->
                val currentRocketList: List<RocketListItem> =
                    (state.value as? VehiclesState.Content)?.vehicleList ?: emptyList()
                val result: DiffUtil.DiffResult =
                    calculateDiff(currentRocketList, newRocketList.body()!!, RocketListItem::name)
                _state.value = VehiclesState.Content(newRocketList.body()!!, result)
            }
        }
    }
}
