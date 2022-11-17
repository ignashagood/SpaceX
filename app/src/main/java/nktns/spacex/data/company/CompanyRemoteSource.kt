package nktns.spacex.data.company

import nktns.spacex.data.SpaceXApi
import nktns.spacex.util.Result
import nktns.spacex.util.withExceptionHandling
import javax.inject.Inject

class CompanyRemoteSource @Inject constructor(private val spaceXApi: SpaceXApi) {

    suspend fun getCompany(): Result<CompanyNetworkModel> = withExceptionHandling {
        spaceXApi.getCompanyInfo()
    }
}
