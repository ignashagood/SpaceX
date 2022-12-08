package nktns.spacex.data

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.vehicles.dragons.DragonRepository
import nktns.spacex.data.vehicles.rockets.RocketRepository
import nktns.spacex.data.vehicles.ships.ShipRepository
import javax.inject.Inject

class VehiclesInteractor @Inject constructor(
    private val rocketRepository: RocketRepository,
    private val shipRepository: ShipRepository,
    private val dragonRepository: DragonRepository,
) {
    private fun getRockets() = rocketRepository.getRockets()
    private fun getShips() = shipRepository.getShips()
    private fun getDragons() = dragonRepository.getDragons()

    fun getVehicles(): Single<List<VehicleModel>> = Single.zip(getDragons(), getRockets()) { a, b ->
        a.plus(b)
    }.zipWith(getShips()) { a, b ->
        a.plus(b)
    }
}
