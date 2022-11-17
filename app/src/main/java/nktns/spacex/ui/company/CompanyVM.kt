package nktns.spacex.ui.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nktns.spacex.data.company.CompanyRepository
import nktns.spacex.data.database.company.asUIModel
import nktns.spacex.util.Result
import timber.log.Timber
import javax.inject.Inject

class CompanyVM @Inject constructor(private val repository: CompanyRepository) : ViewModel() {
    private var _state = MutableStateFlow<CompanyState>(CompanyState.InitialLoading)
    val state: StateFlow<CompanyState> by ::_state

    init {
        fetchCompany()
    }

    private fun fetchCompany() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchCompany().collect { result ->
                when (result) {
                    is Result.Success -> _state.value = CompanyState.Content(result.data.asUIModel())
                    is Result.Error -> Timber.e(result.throwable)
                }
            }
        }
    }
}
