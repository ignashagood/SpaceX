package nktns.spacex.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import nktns.spacex.databinding.VehiclesFragmentBinding
import nktns.spacex.getAppComponent

class VehiclesFragment : Fragment() {
    private var binding: VehiclesFragmentBinding? = null
    private val viewModel: VehiclesVM by viewModels {
        getAppComponent().viewModelsFactory()
    }
    private val adapter = VehiclesAdapter()
    private var contentStateApplied: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = VehiclesFragmentBinding.inflate(inflater, container, false).run {
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
                    is VehiclesState.InitialLoading -> {}
                    is VehiclesState.Content -> applyState(it)
                }
            }
        }
    }

    private fun applyState(state: VehiclesState.Content) {
        if (contentStateApplied) {
            adapter.updateList(state.vehicleList, state.diffResult)
        } else {
            adapter.initList(state.vehicleList)
            contentStateApplied = true
        }
    }
}
