package nktns.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import nktns.spacex.databinding.LaunchesFragmentBinding
import nktns.spacex.getAppComponent

class LaunchesFragment : Fragment() {
    private var binding: LaunchesFragmentBinding? = null
    private var adapter = LaunchesAdapter()
    private val viewModel: LaunchesVM by viewModels {
        getAppComponent().launchesVMFactory()
    }
    private var contentStateApplied: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = LaunchesFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    is LaunchesState.InitialLoading -> {}
                    is LaunchesState.Content -> applyState(it)
                }
            }
        }
    }

    private fun applyState(state: LaunchesState.Content) {
        if (contentStateApplied) {
            adapter.updateList(state.launchList.reversed(), state.diffResult)
        } else {
            adapter.initList(state.launchList.reversed())
            contentStateApplied = true
        }
    }
}
