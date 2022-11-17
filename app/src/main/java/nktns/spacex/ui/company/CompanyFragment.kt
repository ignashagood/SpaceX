package nktns.spacex.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import nktns.spacex.databinding.CompanyFragmentBinding
import nktns.spacex.getAppComponent

class CompanyFragment : Fragment() {
    private var binding: CompanyFragmentBinding? = null
    private val viewModel: CompanyVM by viewModels {
        getAppComponent().companyVMFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = CompanyFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    is CompanyState.InitialLoading -> {}
                    is CompanyState.Content -> {
                        applyState(it)
                    }
                }
            }
        }
    }

    private fun applyState(state: CompanyState.Content) {
        binding?.run {
            name.text = state.company.name
            founder.text = state.company.founder
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
