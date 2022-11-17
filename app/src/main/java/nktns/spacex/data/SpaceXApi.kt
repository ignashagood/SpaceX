package nktns.spacex.data

import nktns.spacex.data.company.CompanyNetworkModel
import nktns.spacex.data.launches.LaunchNetworkModel
import nktns.spacex.data.vehicles.dragons.DragonNetworkModel
import nktns.spacex.data.vehicles.rockets.RocketNetworkModel
import nktns.spacex.data.vehicles.ships.ShipNetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {
    @GET("./rockets")
    suspend fun getRocketList(): Response<List<RocketNetworkModel>>

    @GET("./ships")
    suspend fun getShipList(): Response<List<ShipNetworkModel>>

    @GET("./dragons")
    suspend fun getDragonsList(): Response<List<DragonNetworkModel>>

    @GET("./company")
    suspend fun getCompanyInfo(): Response<CompanyNetworkModel>

    @GET("./launches")
    suspend fun getLaunchList(): Response<List<LaunchNetworkModel>>
}
