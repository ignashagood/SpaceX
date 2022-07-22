package nktns.spacex.data.launches

import nktns.spacex.data.SpaceXApi
import nktns.spacex.util.Result
import nktns.spacex.util.withExceptionHandling
import javax.inject.Inject

class LaunchesRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {

    suspend fun getLaunches(): Result<List<LaunchNetworkModel>> = withExceptionHandling {
        spaceXApi.getLaunchList()
    }

    suspend fun getRocketList() = spaceXApi.getRocketList()

    suspend fun getShipList() = spaceXApi.getShipList()

    suspend fun getCompanyInfo() = spaceXApi.getCompanyInfo()
}
