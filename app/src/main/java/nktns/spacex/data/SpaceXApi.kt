package nktns.spacex.data

import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.company.CompanyNetworkModel
import nktns.spacex.data.launches.LaunchNetworkModel
import nktns.spacex.data.vehicles.dragons.DragonNetworkModel
import nktns.spacex.data.vehicles.rockets.RocketNetworkModel
import nktns.spacex.data.vehicles.ships.ShipNetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {
    @GET("./rockets")
    fun getRocketList(): Single<List<RocketNetworkModel>>

    @GET("./ships")
    fun getShipList(): Single<List<ShipNetworkModel>>

    @GET("./dragons")
    fun getDragonsList(): Single<List<DragonNetworkModel>>

    @GET("./company")
    suspend fun getCompanyInfo(): Response<CompanyNetworkModel>

    @GET("./launches")
    suspend fun getLaunchList(): Response<List<LaunchNetworkModel>>
}
