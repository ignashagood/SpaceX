package nktns.spacex.data.vehicles.rockets

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.rockets.asInteractorModel
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val remoteSource: RocketRemoteSource,
    private val localSource: RocketLocalSource,
) {
    fun getRockets(): Single<List<VehicleModel>> = Single.create {

        if (localSource.isValid()) {
            localSource.getRockets().subscribe({ list ->
                it.onSuccess(list.asInteractorModel())
            }, { throwable ->
                it.onError(throwable)
            })
        }

        remoteSource.getRockets().subscribe({ list ->
            it.onSuccess(list.asInteractorModel())
            localSource.saveRockets(list.asDatabaseModel())
        }, { throwable ->
            it.onError(throwable)
        })
    }
}
