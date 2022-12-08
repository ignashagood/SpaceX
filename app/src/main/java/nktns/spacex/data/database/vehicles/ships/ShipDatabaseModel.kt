package nktns.spacex.data.database.vehicles.ships

import androidx.room.Entity
import androidx.room.PrimaryKey
import nktns.spacex.data.database.Ship
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.VehicleDatabaseModel

@Entity(tableName = "ships")
data class ShipDatabaseModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String,
    val type: String,
    val model: String,
    val speed: String,
    val homePort: String,
    val mass: String,
    val status: String,
    val MMSI: String
) : VehicleDatabaseModel

fun List<ShipDatabaseModel>.asInteractorModel(): List<VehicleModel> {
    return map {
        with(it) {
            Ship(
                id = id,
                name = name,
                image = image,
                type = type,
                model = model,
                speed = speed,
                homePort = homePort,
                status = status,
                MMSI = MMSI,
                mass = mass
            )
        }
    }
}
