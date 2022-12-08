package nktns.spacex.data.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class VehicleModel(open val id: String)

@Parcelize
data class Rocket(
    override val id: String,
    val name: String,
    val image: String,
    val description: String,
    val company: String,
    val country: String,
    val mass: String,
    val height: String,
    val diameter: String,
    val firstFlight: String,
) : VehicleModel(id), Parcelable

@Parcelize
data class Ship(
    override val id: String,
    val name: String,
    val image: String,
    val type: String,
    val model: String,
    val speed: String,
    val homePort: String,
    val status: String,
    val MMSI: String,
    val mass: String
) : VehicleModel(id), Parcelable

@Parcelize
data class Dragon(
    override val id: String,
    val name: String,
    val description: String,
    val height: String,
    val diameter: String,
    val launchPayloadMass: String,
    val returnPayloadMass: String,
    val trunkVolume: String,
    val image: String,
    val heatshieldMaterial: String,
    val heatshieldTemp: String,
    val heatshieldSize: String,
) : VehicleModel(id), Parcelable
