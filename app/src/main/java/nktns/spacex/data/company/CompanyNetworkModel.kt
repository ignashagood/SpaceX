package nktns.spacex.data.company

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nktns.spacex.data.database.company.CompanyDatabaseModel

@Serializable
data class CompanyNetworkModel(

    @SerialName("summary")
    val summary: String? = null,

    @SerialName("coo")
    val coo: String? = null,

    @SerialName("founder")
    val founder: String,

    @SerialName("founded")
    val founded: Int? = null,

    @SerialName("vehicles")
    val vehicles: Int? = null,

    @SerialName("ceo")
    val ceo: String? = null,

    @SerialName("launch_sites")
    val launchSites: Int? = null,

    @SerialName("headquarters")
    val headquarters: Headquarters? = null,

    @SerialName("valuation")
    val valuation: Long? = null,

    @SerialName("name")
    val name: String,

    @SerialName("links")
    val links: Links,

    @SerialName("id")
    val id: String,

    @SerialName("employees")
    val employees: Int? = null,

    @SerialName("test_sites")
    val testSites: Int? = null,

    @SerialName("cto")
    val cto: String? = null,

    @SerialName("cto_propulsion")
    val ctoPropulsion: String? = null,
)

@Serializable
data class Links(

    @SerialName("website")
    val website: String,

    @SerialName("twitter")
    val twitter: String,

    @SerialName("flickr")
    val flickr: String,

    @SerialName("elon_twitter")
    val elonTwitter: String,
)

@Serializable
data class Headquarters(

    @SerialName("address")
    val address: String? = null,

    @SerialName("city")
    val city: String? = null,

    @SerialName("state")
    val state: String? = null,
)

fun CompanyNetworkModel.asDatabaseModel(): CompanyDatabaseModel {
    return CompanyDatabaseModel(
        id = this.id,
        name = this.name,
        founder = this.founder,
        website = this.links.website
    )
}
