package nktns.spacex.data.database.company

import androidx.room.Entity
import androidx.room.PrimaryKey
import nktns.spacex.data.company.CompanyModel

@Entity(tableName = "company")
data class CompanyDatabaseModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val founder: String,
    val website: String,
)

fun CompanyDatabaseModel.asUIModel(): CompanyModel {
    return CompanyModel(
        id = id,
        name = name,
        founder = founder,
        website = website
    )
}
