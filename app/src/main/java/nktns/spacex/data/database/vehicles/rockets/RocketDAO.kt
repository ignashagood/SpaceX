package nktns.spacex.data.database.vehicles.rockets

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RocketDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(rockets: List<RocketDatabaseModel>): Completable

    @Query("select * from rockets")
    fun getRockets(): Single<List<RocketDatabaseModel>>
}
