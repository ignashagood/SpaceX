package nktns.spacex.data.database.launches

import androidx.room.Entity
import androidx.room.PrimaryKey
import nktns.spacex.ui.LaunchUIModel

@Entity(tableName = "launches")
data class LaunchDatabaseModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val date: String,
    val image: String?,
)

fun List<LaunchDatabaseModel>.asUIModel(): List<LaunchUIModel> {
    return map {
        LaunchUIModel(
            name = it.name,
            date = it.date,
            image = it.image
        )
    }
}
