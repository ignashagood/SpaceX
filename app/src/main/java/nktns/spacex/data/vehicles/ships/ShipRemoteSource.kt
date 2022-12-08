package nktns.spacex.data.vehicles.ships

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.SpaceXApi
import javax.inject.Inject

class ShipRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {
    fun getShips(): Single<List<ShipNetworkModel>> = spaceXApi.getShipList()
}
