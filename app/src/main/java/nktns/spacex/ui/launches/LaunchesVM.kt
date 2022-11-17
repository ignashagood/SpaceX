package nktns.spacex.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nktns.spacex.base.calculateDiff
import nktns.spacex.data.database.launches.asUIModel
import nktns.spacex.data.launches.LaunchesRepository
import nktns.spacex.ui.LaunchUIModel
import nktns.spacex.util.Result
import timber.log.Timber
import javax.inject.Inject

class LaunchesVM @Inject constructor(private val repository: LaunchesRepository) : ViewModel() {
    private var _state = MutableStateFlow<LaunchesState>(LaunchesState.InitialLoading)
    val state: StateFlow<LaunchesState> by ::_state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLaunches().collect { newLaunchList ->
                when (newLaunchList) {
                    is Result.Success -> {
                        val currentLaunchList: List<LaunchUIModel> =
                            (state.value as? LaunchesState.Content)?.launchList ?: emptyList()
                        val result: DiffUtil.DiffResult =
                            calculateDiff(
                                currentLaunchList,
                                newLaunchList.data.asUIModel(),
                                LaunchUIModel::name
                            )
                        _state.value = LaunchesState.Content(newLaunchList.data.asUIModel(), result)
                    }
                    is Result.Error -> {
                        Timber.e(newLaunchList.throwable)
                    }
                }
            }
        }
    }
}
