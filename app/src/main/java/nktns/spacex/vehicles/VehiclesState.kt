package nktns.spacex.vehicles

import androidx.recyclerview.widget.DiffUtil
import nktns.spacex.data.RocketListItem

sealed class VehiclesState {
    object InitialLoading : VehiclesState()

    data class Content(
        val vehicleList: List<RocketListItem>,
        val diffResult: DiffUtil.DiffResult
    ) : VehiclesState()
}
