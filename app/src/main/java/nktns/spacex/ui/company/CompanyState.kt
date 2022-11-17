package nktns.spacex.ui.company

import nktns.spacex.data.company.CompanyModel

sealed class CompanyState {
    class Content(val company: CompanyModel) : CompanyState()
    object InitialLoading : CompanyState()
}
