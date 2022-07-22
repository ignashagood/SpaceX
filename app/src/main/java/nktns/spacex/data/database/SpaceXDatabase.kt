package nktns.spacex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import nktns.spacex.data.database.launches.LaunchDAO
import nktns.spacex.data.database.launches.LaunchDatabaseModel
import nktns.spacex.data.database.vehicles.dragons.DragonDAO
import nktns.spacex.data.database.vehicles.dragons.DragonDatabaseModel
import nktns.spacex.data.database.vehicles.rockets.RocketDAO
import nktns.spacex.data.database.vehicles.rockets.RocketDatabaseModel
import nktns.spacex.data.database.vehicles.ships.ShipDAO
import nktns.spacex.data.database.vehicles.ships.ShipDatabaseModel

@Database(
    entities = [
        LaunchDatabaseModel::class,
        RocketDatabaseModel::class,
        DragonDatabaseModel::class,
        ShipDatabaseModel::class
    ],
    version = 1
)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract fun launchDAO(): LaunchDAO
    abstract fun rocketDAO(): RocketDAO
    abstract fun dragonDAO(): DragonDAO
    abstract fun shipDAO(): ShipDAO
}
