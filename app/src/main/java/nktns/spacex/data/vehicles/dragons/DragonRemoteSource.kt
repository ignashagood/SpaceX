package nktns.spacex.data.vehicles.dragons

import nktns.spacex.data.SpaceXApi
import nktns.spacex.util.Result
import nktns.spacex.util.withExceptionHandling
import javax.inject.Inject

class DragonRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {
    suspend fun getDragons(): Result<List<DragonNetworkModel>> = withExceptionHandling {
        spaceXApi.getDragonsList()
    }
}
