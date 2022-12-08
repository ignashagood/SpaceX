package nktns.spacex.data.database.vehicles.dragons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DragonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(dragons: List<DragonDatabaseModel>): Completable

    @Query("select * from dragons")
    fun getDragons(): Single<List<DragonDatabaseModel>>
}
