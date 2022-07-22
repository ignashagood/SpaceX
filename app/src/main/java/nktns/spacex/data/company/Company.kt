package nktns.spacex.data.company

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(

    @SerialName("summary")
    val summary: String? = null,

    @SerialName("coo")
    val coo: String? = null,

    @SerialName("founder")
    val founder: String? = null,

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
    val name: String? = null,

    @SerialName("links")
    val links: Links? = null,

    @SerialName("id")
    val id: String? = null,

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
    val website: String? = null,

    @SerialName("twitter")
    val twitter: String? = null,

    @SerialName("flickr")
    val flickr: String? = null,

    @SerialName("elon_twitter")
    val elonTwitter: String? = null,
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
