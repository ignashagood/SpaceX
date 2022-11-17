package nktns.spacex.data.vehicles.dragons

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.dragons.asInteractorModel
import nktns.spacex.util.Result
import javax.inject.Inject

class DragonRepository @Inject constructor(
    private val remoteSource: DragonRemoteSource,
    private val localSource: DragonLocalSource,
) {
    fun getDragons(): Flow<Result<List<VehicleModel>>> = flow {

        if (localSource.isValid()) {
            emit(Result.Success(localSource.getDragons().asInteractorModel()))
        }

        when (val result = remoteSource.getDragons()) {

            is Result.Error -> emit(result)

            is Result.Success -> {
                emit(Result.Success(result.data.asInteractorModel()))
                localSource.saveDragons(result.data.asDatabaseModel())
            }
        }
    }
}