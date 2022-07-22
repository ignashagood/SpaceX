package nktns.spacex.data.company

import nktns.spacex.data.SpaceXApi
import javax.inject.Inject

class CompanyRepository @Inject constructor(private val spaceXApi: SpaceXApi) {
    suspend fun fetchCompany() = spaceXApi.getCompanyInfo()
}
