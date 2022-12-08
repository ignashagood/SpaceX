package nktns.spacex.data.vehicles.rockets

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.vehicles.rockets.RocketDAO
import nktns.spacex.data.database.vehicles.rockets.RocketDatabaseModel
import timber.log.Timber
import javax.inject.Inject

const val WRITE_DB_ROCKETS_TIME = "write_db_rockets_time"

class RocketLocalSource @Inject constructor(
    private val dao: RocketDAO,
    private val sharedPreferences: SharedPreferences,
) {
    private var cachedAt: Long? = null

    fun isValid(): Boolean { // rename
        return (cachedAt != null)
    }

    fun saveRockets(rockets: List<RocketDatabaseModel>) {
        if (rockets.isEmpty()) return

        dao.addAll(rockets)
        sharedPreferences.edit().putLong(WRITE_DB_ROCKETS_TIME, System.currentTimeMillis()).apply()
        cachedAt = sharedPreferences.getLong(WRITE_DB_ROCKETS_TIME, 0L)
        Timber.d("TIME CACHE - $cachedAt")
    }

    fun getRockets(): Single<List<RocketDatabaseModel>> = dao.getRockets()
}
