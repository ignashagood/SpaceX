package nktns.spacex.data.launches

import android.content.SharedPreferences
import nktns.spacex.data.database.launches.LaunchDAO
import nktns.spacex.data.database.launches.LaunchDatabaseModel
import timber.log.Timber
import javax.inject.Inject

const val WRITE_DB_LAUNCHES_TIME = "write_db_launches_time"

class LaunchesLocalSource @Inject constructor(
    private val dao: LaunchDAO,
    private val sharedPreferences: SharedPreferences,
) {
    private var cachedAt: Long? = null

    fun isValid(): Boolean { // rename
        return (cachedAt != null)
    }

    suspend fun saveLaunches(launches: List<LaunchDatabaseModel>) {
        if (launches.isEmpty()) return

        dao.addAll(launches)
        sharedPreferences.edit().putLong(WRITE_DB_LAUNCHES_TIME, System.currentTimeMillis()).apply()
        cachedAt = sharedPreferences.getLong(WRITE_DB_LAUNCHES_TIME, 0L)
        Timber.d("TIME CACHE - $cachedAt")
    }

    fun getLaunches(): List<LaunchDatabaseModel> = dao.getLaunches()
}
