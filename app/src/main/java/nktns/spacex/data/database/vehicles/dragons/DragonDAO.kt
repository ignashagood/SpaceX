package nktns.spacex.data.database.vehicles.dragons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DragonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(dragons: List<DragonDatabaseModel>)

    @Query("select * from dragons")
    fun getDragons(): List<DragonDatabaseModel>
}