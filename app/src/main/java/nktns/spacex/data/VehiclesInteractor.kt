package nktns.spacex.data

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.vehicles.dragons.DragonRepository
import nktns.spacex.data.vehicles.rockets.RocketRepository
import nktns.spacex.data.vehicles.ships.ShipRepository
import nktns.spacex.util.Result
import javax.inject.Inject

class VehiclesInteractor @Inject constructor(
    private val rocketRepository: RocketRepository,
    private val shipRepository: ShipRepository,
    private val dragonRepository: DragonRepository,
) {
    private fun getRockets() = rocketRepository.getRockets()
    private fun getShips() = shipRepository.getShips()
    private fun getDragons() = dragonRepository.getDragons()

    @OptIn(FlowPreview::class)
    fun getVehicles(): Flow<Result<List<VehicleModel>>> =
        getRockets()
            .combine(getDragons()) { rockets, dragons ->
                if (rockets is Result.Error && dragons is Result.Error) {
                    Result.Error(Exception())
                } else {
                    val rocketList = (rockets as? Result.Success)?.data ?: emptyList()
                    val dragonList = (dragons as? Result.Success)?.data ?: emptyList()
                    Result.Success(rocketList + dragonList)
                }
            }
            .combine(getShips()) { vehicles, ships ->
                if (vehicles is Result.Error && ships is Result.Error) {
                    Result.Error(Exception())
                } else {
                    val vehicleList = (vehicles as? Result.Success)?.data ?: emptyList()
                    val shipList = (ships as? Result.Success)?.data ?: emptyList()
                    Result.Success(vehicleList + shipList)
                }
            }
}
