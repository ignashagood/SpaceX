package nktns.spacex.ui.vehicles.vehicleList

import androidx.recyclerview.widget.DiffUtil
import nktns.spacex.data.database.VehicleModel

sealed class VehicleListState {
    object InitialLoading : VehicleListState()

    data class Content(
        val vehicleList: List<VehicleModel>,
        val diffResult: DiffUtil.DiffResult
    ) : VehicleListState()
}
