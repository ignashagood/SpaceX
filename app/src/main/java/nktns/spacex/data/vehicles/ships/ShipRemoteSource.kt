package nktns.spacex.data.vehicles.ships

import nktns.spacex.data.SpaceXApi
import nktns.spacex.util.Result
import nktns.spacex.util.withExceptionHandling
import javax.inject.Inject

class ShipRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {
    suspend fun getShips(): Result<List<ShipNetworkModel>> = withExceptionHandling {
        spaceXApi.getShipList()
    }
}
