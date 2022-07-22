package nktns.spacex.data.vehicles.ships

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.ships.asInteractorModel
import nktns.spacex.util.Result
import timber.log.Timber
import javax.inject.Inject

class ShipRepository @Inject constructor(
    private val remoteSource: ShipRemoteSource,
    private val localSource: ShipLocalSource,
) {
    fun getShips(): Flow<Result<List<VehicleModel>>> = flow {

        if (localSource.isValid()) {
            emit(Result.Success(localSource.getShips().asInteractorModel()))
        }

        when (val result = remoteSource.getShips()) {

            is Result.Error -> {
                emit(result)
                Timber.e(result.throwable)
            }

            is Result.Success -> {
                emit(Result.Success(result.data.asInteractorModel()))
                localSource.saveShips(result.data.asDatabaseModel())
            }
        }
    }
}
