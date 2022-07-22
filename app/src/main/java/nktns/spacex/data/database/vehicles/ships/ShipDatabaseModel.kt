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
    val image: String?,
    val type: String,
    val model: String?,
    val speed: Int?,
    val homePort: String?,
    val status: String?,
    val MMSI: Int?
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
                speed = speed.toString(),
                homePort = homePort,
                status = status,
                MMSI = MMSI.toString()
            )
        }
    }
}
