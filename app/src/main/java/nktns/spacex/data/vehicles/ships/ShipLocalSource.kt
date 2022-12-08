package nktns.spacex.data.vehicles.ships

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.vehicles.ships.ShipDAO
import nktns.spacex.data.database.vehicles.ships.ShipDatabaseModel
import timber.log.Timber
import javax.inject.Inject

const val WRITE_DB_SHIPS_TIME = "write_db_ships_time"

class ShipLocalSource @Inject constructor(
    private val dao: ShipDAO,
    private val sharedPreferences: SharedPreferences,
) {
    private var cachedAt: Long? = null

    fun isValid(): Boolean { // rename
        return (cachedAt != null)
    }

    fun saveShips(ships: List<ShipDatabaseModel>) {
        if (ships.isEmpty()) return

        dao.addAll(ships)
        sharedPreferences.edit().putLong(WRITE_DB_SHIPS_TIME, System.currentTimeMillis()).apply()
        cachedAt = sharedPreferences.getLong(WRITE_DB_SHIPS_TIME, 0L)
        Timber.d("TIME CACHE - $cachedAt")
    }

    fun getShips(): Single<List<ShipDatabaseModel>> = dao.getShips()
}