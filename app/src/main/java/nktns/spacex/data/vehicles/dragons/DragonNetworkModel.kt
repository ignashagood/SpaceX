package nktns.spacex.data.vehicles.dragons

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nktns.spacex.data.VehicleUIModel
import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.dragons.DragonDatabaseModel

@Parcelize
@Serializable
data class DragonNetworkModel(

    @SerialName("launch_payload_mass")
    val launchPayloadMass: LaunchPayloadMass,

    @SerialName("height_w_trunk")
    val height: Height,

    @SerialName("dry_mass_lb")
    val dryMassLb: Int? = null,

    @SerialName("crew_capacity")
    val crewCapacity: Int? = null,

    @SerialName("heat_shield")
    val heatShield: HeatShield,

    @SerialName("active")
    val active: Boolean? = null,

    @SerialName("description")
    val description: String,

    @SerialName("return_payload_mass")
    val returnPayloadMass: ReturnPayloadMass,

    @SerialName("trunk")
    val trunk: Trunk,

    @SerialName("type")
    val type: String? = null,

    @SerialName("first_flight")
    val firstFlight: String? = null,

    @SerialName("orbit_duration_yr")
    val orbitDurationYr: Int? = null,

    @SerialName("diameter")
    val diameter: Diameter,

    @SerialName("flickr_images")
    val flickrImages: List<String>,

    @SerialName("name")
    val name: String,

    @SerialName("wikipedia")
    val wikipedia: String? = null,

    @SerialName("id")
    val id: String,

) : Parcelable

@Parcelize
@Serializable
data class TrunkVolume(

    @SerialName("cubic_feet")
    val cubicFeet: Int? = null,

    @SerialName("cubic_meters")
    val cubicMeters: Int,
) : Parcelable

@Parcelize
@Serializable
data class Trunk(

    @SerialName("trunk_volume")
    val trunkVolume: TrunkVolume,
) : Parcelable

@Parcelize
@Serializable
data class ReturnPayloadMass(

    @SerialName("lb")
    val lb: Int,

    @SerialName("kg")
    val kg: Int,
) : Parcelable

@Parcelize
@Serializable
data class LaunchPayloadMass(

    @SerialName("lb")
    val lb: Int,

    @SerialName("kg")
    val kg: Int,
) : Parcelable

@Parcelize
@Serializable
data class Height(
    @SerialName("meters")
    val meters: Double,

    @SerialName("feet")
    val feet: Double,
) : Parcelable

@Parcelize
@Serializable
data class HeatShield(

    @SerialName("material")
    val material: String,

    @SerialName("temp_degrees")
    val tempDegrees: Int,

    @SerialName("size_meters")
    val sizeMeters: Double,
) : Parcelable

@Parcelize
@Serializable
data class Diameter(

    @SerialName("feet")
    val feet: Int? = null,

    @SerialName("meters")
    val meters: Double,
) : Parcelable

fun List<DragonNetworkModel>.asDatabaseModel(): List<DragonDatabaseModel> {
    return map {
        with(it) {
            DragonDatabaseModel(
                id = id,
                name = name,
                description = description,
                height = height.meters,
                diameter = diameter.meters,
                launchPayloadMass = launchPayloadMass.kg,
                returnPayloadMass = returnPayloadMass.kg,
                trunkVolume = trunk.trunkVolume.cubicMeters,
                image = flickrImages[0],
                heatshieldMaterial = heatShield.material,
                heatshieldSize = heatShield.sizeMeters,
                heatshieldTemp = heatShield.tempDegrees
            )
        }
    }
}

fun List<DragonNetworkModel>.asInteractorModel(): List<VehicleModel> {
    return map {
        with(it) {
            Dragon(
                id = id,
                name = name,
                description = description,
                height = height.meters.toString(),
                diameter = diameter.meters.toString(),
                launchPayloadMass = launchPayloadMass.kg.toString(),
                returnPayloadMass = returnPayloadMass.kg.toString(),
                trunkVolume = trunk.trunkVolume.cubicMeters.toString(),
                image = flickrImages[0],
                heatshieldMaterial = heatShield.material,
                heatshieldTemp = heatShield.tempDegrees.toString(),
                heatshieldSize = heatShield.sizeMeters.toString()
            )
        }
    }
}
