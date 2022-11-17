package nktns.spacex.data.vehicles.rockets

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.rockets.asInteractorModel
import nktns.spacex.util.Result
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val remoteSource: RocketRemoteSource,
    private val localSource: RocketLocalSource,
) {
    fun getRockets(): Flow<Result<List<VehicleModel>>> = flow {

        if (localSource.isValid()) {
            emit(Result.Success(localSource.getRockets().asInteractorModel()))
        }

        when (val result = remoteSource.getRockets()) {

            is Result.Error -> emit(result)

            is Result.Success -> {
                emit(Result.Success(result.data.asInteractorModel()))
                localSource.saveRockets(result.data.asDatabaseModel())
            }
        }
    }
}
