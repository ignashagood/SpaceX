package nktns.spacex.util

import android.view.View
import nktns.spacex.data.database.VehicleModel

interface OnVehicleClickListener {
    fun onItemClickListener(vehicle: VehicleModel, view: View)
}
