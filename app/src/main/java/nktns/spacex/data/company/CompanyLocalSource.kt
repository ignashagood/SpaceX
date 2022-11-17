package nktns.spacex.data.company

import android.content.SharedPreferences
import nktns.spacex.data.database.company.CompanyDAO
import nktns.spacex.data.database.company.CompanyDatabaseModel
import timber.log.Timber
import javax.inject.Inject

const val WRITE_DB_COMPANY_TIME = "write_db_company_time"

class CompanyLocalSource @Inject constructor(
    private val dao: CompanyDAO,
    private val sharedPreferences: SharedPreferences,
) {
    private var cachedAt: Long? = null

    fun isValid(): Boolean { // rename
        return (cachedAt != null)
    }

    suspend fun saveCompany(company: CompanyDatabaseModel) {

        dao.add(company)
        sharedPreferences.edit().putLong(WRITE_DB_COMPANY_TIME, System.currentTimeMillis()).apply()
        cachedAt = sharedPreferences.getLong(WRITE_DB_COMPANY_TIME, 0L)
        Timber.d("TIME CACHE - $cachedAt")
    }

    fun getCompany(): CompanyDatabaseModel = dao.getCompany()
}
