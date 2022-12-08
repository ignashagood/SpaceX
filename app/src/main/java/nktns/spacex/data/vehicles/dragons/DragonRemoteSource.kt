package nktns.spacex.data.vehicles.dragons

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.SpaceXApi
import javax.inject.Inject

class DragonRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {
    fun getDragons(): Single<List<DragonNetworkModel>> = spaceXApi.getDragonsList()
}
