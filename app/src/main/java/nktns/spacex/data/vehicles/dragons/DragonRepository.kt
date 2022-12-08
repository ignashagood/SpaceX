package nktns.spacex.data.vehicles.dragons

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.dragons.asInteractorModel
import javax.inject.Inject

class DragonRepository @Inject constructor(
    private val remoteSource: DragonRemoteSource,
    private val localSource: DragonLocalSource,
) {
    fun getDragons(): Single<List<VehicleModel>> = Single.create {

        if (localSource.isValid()) {
            localSource.getDragons().subscribe({ list ->
                it.onSuccess(list.asInteractorModel())
            }, { throwable ->
                it.onError(throwable)
            })
        }

        remoteSource.getDragons().subscribe({ list ->
            it.onSuccess(list.asInteractorModel())
            localSource.saveDragons(list.asDatabaseModel())
        }, { throwable ->
            it.onError(throwable)
        })
    }
}
