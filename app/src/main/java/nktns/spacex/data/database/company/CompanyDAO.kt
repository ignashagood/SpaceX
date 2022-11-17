package nktns.spacex.data.database.company

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompanyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(company: CompanyDatabaseModel)

    @Query("select * from company")
    fun getCompany(): CompanyDatabaseModel
}
