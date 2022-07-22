package nktns.spacex.ui

import nktns.spacex.data.VehicleUIModel

data class ShipUIModel(
    val name: String,
    val image: String?,
    val type: String,
    val model: String?,
    val speed: String,
    val homePort: String?,
    val status: String?,
    val MMSI: String
) : VehicleUIModel
