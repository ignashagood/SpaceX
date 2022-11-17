package nktns.spacex.data.database.vehicles.dragons

import androidx.room.Entity
import androidx.room.PrimaryKey
import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.VehicleDatabaseModel

@Entity(tableName = "dragons")
data class DragonDatabaseModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val height: Double,
    val diameter: Double,
    val launchPayloadMass: Int,
    val returnPayloadMass: Int,
    val trunkVolume: Int,
    val image: String,
    val heatshieldMaterial: String,
    val heatshieldTemp: Int,
    val heatshieldSize: Double,
) : VehicleDatabaseModel

fun List<DragonDatabaseModel>.asInteractorModel(): List<VehicleModel> {
    return map {
        with(it) {
            Dragon(
                id = id,
                name = name,
                description = description,
                height = height.toString(),
                diameter = diameter.toString(),
                launchPayloadMass = launchPayloadMass.toString(),
                returnPayloadMass = returnPayloadMass.toString(),
                trunkVolume = trunkVolume.toString(),
                image = image,
                heatshieldMaterial = heatshieldMaterial,
                heatshieldTemp = heatshieldTemp.toString(),
                heatshieldSize = heatshieldSize.toString()
            )
        }
    }
}
