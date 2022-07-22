package nktns.spacex.ui.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nktns.spacex.data.company.CompanyRepository
import javax.inject.Inject

class CompanyVM @Inject constructor(private val repository: CompanyRepository) : ViewModel() {
    private var _state = MutableStateFlow<CompanyState>(CompanyState.InitialLoading)
    val state: StateFlow<CompanyState> by ::_state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val company = repository.fetchCompany().body()
            company?.let {
                _state.value = CompanyState.Content(company)
            }
        }
    }
}
