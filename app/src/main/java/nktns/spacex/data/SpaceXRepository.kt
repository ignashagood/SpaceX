package nktns.spacex.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class SpaceXRepository @Inject constructor(private val spaceXApi: SpaceXApi) {

    suspend fun fetchAllRockets(): Flow<Response<List<RocketListItem>>> {
        return flow {
            emit(spaceXApi.getRocketList())
        }.flowOn(Dispatchers.IO)
    }
}
