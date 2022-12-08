package nktns.spacex.data.vehicles.dragons

import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Single
import nktns.spacex.data.database.vehicles.dragons.DragonDAO
import nktns.spacex.data.database.vehicles.dragons.DragonDatabaseModel
import timber.log.Timber
import javax.inject.Inject

const val WRITE_DB_DRAGONS_TIME = "write_db_dragons_time"

class DragonLocalSource @Inject constructor(
    private val dao: DragonDAO,
    private val sharedPreferences: SharedPreferences,
) {
    private var cachedAt: Long? = null

    fun isValid(): Boolean { // rename
        return (cachedAt != null)
    }

    fun saveDragons(dragons: List<DragonDatabaseModel>) {
        if (dragons.isEmpty()) return

        dao.addAll(dragons)
        sharedPreferences.edit().putLong(WRITE_DB_DRAGONS_TIME, System.currentTimeMillis()).apply()
        cachedAt = sharedPreferences.getLong(WRITE_DB_DRAGONS_TIME, 0L)
        Timber.d("TIME CACHE - $cachedAt")
    }

    fun getDragons(): Single<List<DragonDatabaseModel>> = dao.getDragons()
}