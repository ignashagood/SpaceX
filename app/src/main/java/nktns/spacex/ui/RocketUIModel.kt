package nktns.spacex.ui

import nktns.spacex.data.VehicleUIModel

data class RocketUIModel(
    val name: String,
    val image: String,
    val description: String,
    val company: String,
    val country: String,
    val mass: String,
    val height: String,
    val diameter: String,
    val firstFlight: String
) : VehicleUIModel
