package nktns.spacex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketListItem(

    @SerialName("second_stage")
    val secondStage: SecondStage? = null,

    @SerialName("country")
    val country: String? = null,

    @SerialName("mass")
    val mass: Mass? = null,

    @SerialName("active")
    val active: Boolean? = null,

    @SerialName("cost_per_launch")
    val costPerLaunch: Int? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("payload_weights")
    val payloadWeights: List<PayloadWeightsItem?>? = null,

    @SerialName("first_flight")
    val firstFlight: String? = null,

    @SerialName("landing_legs")
    val landingLegs: LandingLegs? = null,

    @SerialName("diameter")
    val diameter: Diameter? = null,

    @SerialName("flickr_images")
    val flickrImages: List<String?>? = null,

    @SerialName("first_stage")
    val firstStage: FirstStage? = null,

    @SerialName("engines")
    val engines: Engines? = null,

    @SerialName("name")
    val name: String,

    @SerialName("stages")
    val stages: Int? = null,

    @SerialName("boosters")
    val boosters: Int? = null,

    @SerialName("company")
    val company: String? = null,

    @SerialName("success_rate_pct")
    val successRatePct: Int? = null,

    @SerialName("wikipedia")
    val wikipedia: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("height")
    val height: Height? = null
)

@Serializable
data class CompositeFairing(

    @SerialName("diameter")
    val diameter: Diameter? = null,

    @SerialName("height")
    val height: Height? = null
)

@Serializable
data class ThrustSeaLevel(

    @SerialName("lbf")
    val lbf: Int? = null,

    @SerialName("kN")
    val kN: Int? = null
)

@Serializable
data class Mass(

    @SerialName("lb")
    val lb: Int? = null,

    @SerialName("kg")
    val kg: Int? = null
)

@Serializable
data class PayloadWeightsItem(

    @SerialName("lb")
    val lb: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("kg")
    val kg: Int? = null
)

@Serializable
data class FirstStage(

    @SerialName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel? = null,

    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Double? = null,

    @SerialName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum? = null,

    @SerialName("engines")
    val engines: Int? = null,

    @SerialName("burn_time_sec")
    val burnTimeSec: Int? = null,

    @SerialName("reusable")
    val reusable: Boolean? = null
)

@Serializable
data class ThrustVacuum(

    @SerialName("lbf")
    val lbf: Int? = null,

    @SerialName("kN")
    val kN: Int? = null
)

@Serializable
data class Isp(

    @SerialName("vacuum")
    val vacuum: Int? = null,

    @SerialName("sea_level")
    val seaLevel: Int? = null
)

@Serializable
data class Engines(

    @SerialName("layout")
    val layout: String? = null,

    @SerialName("number")
    val number: Int? = null,

    @SerialName("propellant_1")
    val propellant1: String? = null,

    @SerialName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel? = null,

    @SerialName("engine_loss_max")
    val engineLossMax: Int? = null,

    @SerialName("thrust_to_weight")
    val thrustToWeight: Double? = null,

    @SerialName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum? = null,

    @SerialName("isp")
    val isp: Isp? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("version")
    val version: String? = null,

    @SerialName("propellant_2")
    val propellant2: String? = null
)

@Serializable
data class Diameter(

    @SerialName("feet")
    val feet: Double? = null,

    @SerialName("meters")
    val meters: Double? = null
)

@Serializable
data class Height(

    @SerialName("feet")
    val feet: Double? = null,

    @SerialName("meters")
    val meters: Double? = null
)

@Serializable
data class LandingLegs(

    @SerialName("number")
    val number: Int? = null,

    @SerialName("material")
    val material: String? = null
)

@Serializable
data class Payloads(

    @SerialName("composite_fairing")
    val compositeFairing: CompositeFairing? = null,

    @SerialName("option_1")
    val option1: String? = null
)

@Serializable
data class Thrust(

    @SerialName("lbf")
    val lbf: Int? = null,

    @SerialName("kN")
    val kN: Int? = null
)

@Serializable
data class SecondStage(

    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Double? = null,

    @SerialName("payloads")
    val payloads: Payloads? = null,

    @SerialName("engines")
    val engines: Int? = null,

    @SerialName("burn_time_sec")
    val burnTimeSec: Int? = null,

    @SerialName("thrust")
    val thrust: Thrust? = null,

    @SerialName("reusable")
    val reusable: Boolean? = null
)
