package nktns.spacex.data.launches

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nktns.spacex.data.database.launches.LaunchDatabaseModel
import nktns.spacex.util.Result
import javax.inject.Inject

class LaunchesRepository @Inject constructor(
    private val remoteSource: LaunchesRemoteSource,
    private val localSource: LaunchesLocalSource,
) {
    fun getLaunches(): Flow<Result<List<LaunchDatabaseModel>>> = flow {

        if (localSource.isValid()) {
            emit(Result.Success(localSource.getLaunches()))
        }

        when (val result = remoteSource.getLaunches()) {

            is Result.Error -> emit(result)

            is Result.Success -> {
                emit(Result.Success(result.data.asDatabaseModel()))
                localSource.saveLaunches(result.data.asDatabaseModel())
            }
        }
    }
}
