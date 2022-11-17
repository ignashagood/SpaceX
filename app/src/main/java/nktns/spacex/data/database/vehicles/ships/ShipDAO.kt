package nktns.spacex.data.database.vehicles.ships

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShipDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(ships: List<ShipDatabaseModel>)

    @Query("select * from ships")
    fun getShips(): List<ShipDatabaseModel>
}
