package nktns.spacex.data.vehicles.ships

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nktns.spacex.data.database.Ship
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.ships.ShipDatabaseModel

@Parcelize
@Serializable
data class ShipNetworkModel(

    @SerialName("image")
    val image: String? = null,

    @SerialName("speed_kn")
    val speedKn: Int? = null,

    @SerialName("mmsi")
    val mmsi: Int? = null,

    @SerialName("roles")
    val roles: List<String?>? = null,

    @SerialName("link")
    val link: String? = null,

    @SerialName("active")
    val active: Boolean? = null,

    @SerialName("imo")
    val imo: Int? = null,

    @SerialName("type")
    val type: String,

    @SerialName("mass_kg")
    val massKg: Int? = null,

    @SerialName("abs")
    val abs: Int? = null,

    @SerialName("name")
    val name: String,

    @SerialName("model")
    val model: String? = null,

    @SerialName("year_built")
    val yearBuilt: Int? = null,

    @SerialName("id")
    val id: String,

    @SerialName("home_port")
    val homePort: String? = null,

    @SerialName("launches")
    val launches: List<String?>? = null,

    @SerialName("status")
    val status: String? = null,
) : Parcelable

fun List<ShipNetworkModel>.asDatabaseModel(): List<ShipDatabaseModel> {
    return map {
        with(it) {
            ShipDatabaseModel(
                id = id,
                name = name,
                image = image,
                type = type,
                model = model,
                speed = speedKn,
                homePort = homePort,
                status = status,
                MMSI = mmsi,
                mass = massKg.toString()
            )
        }
    }
}

fun List<ShipNetworkModel>.asInteractorModel(): List<VehicleModel> {
    return map {
        with(it) {
            Ship(
                id = id,
                name = name,
                image = image,
                type = type,
                model = model,
                speed = speedKn.toString(),
                homePort = homePort,
                status = status,
                MMSI = mmsi.toString(),
                mass = massKg.toString()
            )
        }
    }
}