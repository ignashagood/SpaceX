package nktns.spacex.ui.vehicles.vehicleList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class VehiclesVMFactory @Inject constructor(
    private val viewModelProvider: Provider<VehicleListVM>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }
}
