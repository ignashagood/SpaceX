package nktns.spacex.data.vehicles.rockets

import nktns.spacex.data.SpaceXApi
import nktns.spacex.util.Result
import nktns.spacex.util.withExceptionHandling
import javax.inject.Inject

class RocketRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {
    suspend fun getRockets(): Result<List<RocketNetworkModel>> = withExceptionHandling {
        spaceXApi.getRocketList()
    }
}
