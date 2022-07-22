package nktns.spacex.data.database.vehicles.rockets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RocketDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(rockets: List<RocketDatabaseModel>)

    @Query("select * from rockets")
    fun getRockets(): List<RocketDatabaseModel>
}