package nktns.spacex.data.vehicles.rockets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nktns.spacex.data.VehicleUIModel
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.rockets.RocketDatabaseModel
import nktns.spacex.ui.RocketUIModel

@Parcelize
@Serializable
data class RocketNetworkModel(

    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("type")
    val type: String,

    @SerialName("country")
    val country: String,

    @SerialName("mass")
    val mass: Mass,

    @SerialName("active")
    val active: Boolean? = null,

    @SerialName("first_flight")
    val firstFlight: String,

    @SerialName("flickr_images")
    val flickrImages: List<String>,

    @SerialName("company")
    val company: String,

    @SerialName("wikipedia")
    val wikipedia: String,

    @SerialName("height")
    val height: Height,

    @SerialName("diameter")
    val diameter: Diameter,
) : Parcelable

@Parcelize
@Serializable
data class Mass(

    @SerialName("lb")
    val lb: Int,

    @SerialName("kg")
    val kg: Int
) : Parcelable

@Parcelize
@Serializable
data class Height(

    @SerialName("feet")
    val feet: Double,

    @SerialName("meters")
    val meters: Double
) : Parcelable

@Parcelize
@Serializable
data class Diameter(

    @SerialName("feet")
    val feet: Double,

    @SerialName("meters")
    val meters: Double,
) : Parcelable

fun List<RocketNetworkModel>.asDatabaseModel(): List<RocketDatabaseModel> {
    return map {
        with(it) {
            RocketDatabaseModel(
                id = id,
                name = name,
                image = flickrImages[0],
                description = description,
                company = company,
                country = country,
                mass = mass.kg,
                height = height.meters,
                diameter = diameter.meters,
                firstFlight = firstFlight
            )
        }
    }
}

fun List<RocketNetworkModel>.asInteractorModel(): List<VehicleModel> {
    return map {
        with(it) {
            Rocket(
                id = id,
                name = name,
                image = flickrImages[0],
                description = description,
                company = company,
                country = country,
                mass = mass.kg.toString(),
                height = height.meters.toString(),
                diameter = diameter.meters.toString(),
                firstFlight = firstFlight
            )
        }
    }
}

