package nktns.spacex.data

import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {
    @GET("./rockets")
    suspend fun getRocketList(): Response<List<RocketListItem>>
}
