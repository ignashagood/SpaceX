package nktns.spacex.ui

import nktns.spacex.data.VehicleUIModel

data class DragonUIModel(
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
    val heatshieldSize: String
) : VehicleUIModel
