package nktns.spacex.data.database.vehicles.ships

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ShipDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(ships: List<ShipDatabaseModel>): Completable

    @Query("select * from ships")
    fun getShips(): Single<List<ShipDatabaseModel>>
}
