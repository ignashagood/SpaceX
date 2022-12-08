package nktns.spacex.data.vehicles.rockets

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.SpaceXApi
import javax.inject.Inject

class RocketRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {
    fun getRockets(): Single<List<RocketNetworkModel>> = spaceXApi.getRocketList()
}
