package nktns.spacex.data.database.launches

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LaunchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(launches: List<LaunchDatabaseModel>)

    @Query("select * from launches")
    fun getLaunches(): List<LaunchDatabaseModel>
}