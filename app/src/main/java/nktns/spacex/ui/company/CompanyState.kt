package nktns.spacex.ui.company

import nktns.spacex.data.company.Company

sealed class CompanyState {
    class Content(val company: Company) : CompanyState()
    object InitialLoading : CompanyState()
}
