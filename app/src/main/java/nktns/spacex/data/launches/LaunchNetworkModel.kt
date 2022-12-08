package nktns.spacex.data.launches

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nktns.spacex.data.database.launches.LaunchDatabaseModel
import nktns.spacex.ui.LaunchUIModel
import nktns.spacex.util.convertLongToTime

@Serializable
data class LaunchList(val launches: List<LaunchNetworkModel>)

@Serializable
data class LaunchNetworkModel(

    @SerialName("launchpad")
    val launchpad: String? = null,

    @SerialName("rocket")
    val rocket: String? = null,

    @SerialName("auto_update")
    val autoUpdate: Boolean? = null,

    @SerialName("links")
    val links: LaunchLinks? = null,

    @SerialName("details")
    val details: String? = null,

    @SerialName("id")
    val id: String,

    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String? = null,

    @SerialName("date_local")
    val dateLocal: String? = null,

    @SerialName("flight_number")
    val flightNumber: Int? = null,

    @SerialName("date_unix")
    val dateUnix: Long,

    @SerialName("static_fire_date_unix")
    val staticFireDateUnix: Int? = null,

    @SerialName("success")
    val success: Boolean? = null,

    @SerialName("name")
    val name: String,

    @SerialName("window")
    val window: Int? = null,

    @SerialName("upcoming")
    val upcoming: Boolean? = null,
)

@Serializable
data class LaunchLinks(
    @SerialName("patch")
    val patch: Patch? = null,

    @SerialName("wikipedia")
    val wikipedia: String? = null,

    @SerialName("youtube_id")
    val youtubeId: String? = null,
)

@Serializable
data class Patch(
    @SerialName("small")
    val small: String? = null,

    @SerialName("large")
    val large: String? = null,
)

fun List<LaunchNetworkModel>.asUIModel(): List<LaunchUIModel> {
    return map {
        LaunchUIModel(
            name = it.name,
            date = it.dateUnix.convertLongToTime(),
            image = it.links?.patch?.small
        )
    }
}

fun List<LaunchNetworkModel>.asDatabaseModel(): List<LaunchDatabaseModel> {
    return map {
        LaunchDatabaseModel(
            id = it.id,
            name = it.name,
            date = it.dateUnix.convertLongToTime(),
            image = it.links?.patch?.small
        )
    }
}
