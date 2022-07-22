package nktns.spacex.data.database.vehicles.rockets

import androidx.room.Entity
import androidx.room.PrimaryKey
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.data.database.vehicles.VehicleDatabaseModel

@Entity(tableName = "rockets")
data class RocketDatabaseModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val company: String,
    val country: String,
    val mass: Int,
    val height: Double,
    val diameter: Double,
    val firstFlight: String
) : VehicleDatabaseModel

fun List<RocketDatabaseModel>.asInteractorModel(): List<VehicleModel> {
    return map {
        with(it) {
            Rocket(
                id = id,
                name = name,
                image = image,
                description = description,
                company = company,
                country = country,
                mass = mass.toString(),
                height = height.toString(),
                diameter = diameter.toString(),
                firstFlight = firstFlight
            )
        }
    }
}
