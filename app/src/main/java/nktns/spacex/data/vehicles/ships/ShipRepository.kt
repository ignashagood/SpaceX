package nktns.spacex.data.vehicles.ships

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.ships.asInteractorModel
import javax.inject.Inject

class ShipRepository @Inject constructor(
    private val remoteSource: ShipRemoteSource,
    private val localSource: ShipLocalSource,
) {
    fun getShips(): Single<List<VehicleModel>> = Single.create {

        if (localSource.isValid()) {
            localSource.getShips().subscribe({ list ->
                it.onSuccess(list.asInteractorModel())
            }, { throwable ->
                it.onError(throwable)
            })
        }

        remoteSource.getShips().subscribe({ list ->
            it.onSuccess(list.asInteractorModel())
            localSource.saveShips(list.asDatabaseModel())
        }, { throwable ->
            it.onError(throwable)
        })
    }
}
