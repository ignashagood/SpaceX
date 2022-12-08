package nktns.spacex.ui.vehicles.vehicleList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.drawee.view.SimpleDraweeView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import nktns.spacex.R
import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.Ship
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.databinding.VehiclesFragmentBinding
import nktns.spacex.getAppComponent
import nktns.spacex.ui.vehicles.dragon.DragonInfoFragment
import nktns.spacex.ui.vehicles.rocket.RocketInfoFragment
import nktns.spacex.ui.vehicles.ship.ShipInfoFragment
import nktns.spacex.util.OnVehicleClickListener

class VehicleListFragment : Fragment(), OnVehicleClickListener {
    private var binding: VehiclesFragmentBinding? = null
    private val viewModel: VehicleListVM by viewModels {
        getAppComponent().vehiclesVMFactory()
    }
    private val adapter = ListDelegationAdapter(
        VehicleAdapterDelegates.rocketAdapterDelegate(this),
        VehicleAdapterDelegates.shipAdapterDelegate(this),
        VehicleAdapterDelegates.dragonAdapterDelegate(this)
    )
    private var contentStateApplied: Boolean = false
    private var lastClickedElement: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.action.collect {
                when (it) {
                    is VehicleListAction.ShowRocket -> showRocketInfo(
                        it.rocket,
                    )
                    is VehicleListAction.ShowShip -> showShipInfo(
                        it.ship,
                    )
                    is VehicleListAction.ShowDragon -> showDragonInfo(
                        it.dragon,
                    )
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = VehiclesFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is VehicleListState.InitialLoading -> {}
                is VehicleListState.Content -> {
                    applyState(it)
                }
            }
        }
    }

    private fun applyState(state: VehicleListState.Content) {
        if (contentStateApplied) {
            updateList(state.vehicleList, state.diffResult)
        } else {
            initList(state.vehicleList)
            contentStateApplied = true
        }
    }

    private fun updateList(vehicleList: List<VehicleModel>, diffResult: DiffUtil.DiffResult) {
        adapter.items = vehicleList
        diffResult.dispatchUpdatesTo(adapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initList(vehicleList: List<VehicleModel>) {
        adapter.apply {
            items = vehicleList
            notifyDataSetChanged()
        }
    }

    override fun onItemClickListener(vehicle: VehicleModel, view: View) {
        lastClickedElement = view
        viewModel.onItemClick(vehicle)
    }

    private fun showRocketInfo(rocket: Rocket) {
        lastClickedElement?.let {
            val imageView = it.findViewById<SimpleDraweeView>(R.id.vehicle_image)
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .addSharedElement(
                    imageView,
                    imageView.transitionName
                )
                .replace(
                    R.id.fragment_container,
                    RocketInfoFragment.newInstance(rocket, rocket.id)
                )
                .addToBackStack("rocket info")
                .commit()
            lastClickedElement = null
        }
    }

    private fun showShipInfo(ship: Ship) {
        lastClickedElement?.let {
            val imageView = it.findViewById<SimpleDraweeView>(R.id.vehicle_image)
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .addSharedElement(
                    imageView,
                    imageView.transitionName
                )
                .replace(
                    R.id.fragment_container,
                    ShipInfoFragment.newInstance(ship, ship.id)
                )
                .addToBackStack(null)
                .commit()
            lastClickedElement = null
        }
    }

    private fun showDragonInfo(dragon: Dragon) {
        lastClickedElement?.let {
            val imageView = it.findViewById<SimpleDraweeView>(R.id.vehicle_image)
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .addSharedElement(
                    imageView,
                    imageView.transitionName
                )
                .replace(
                    R.id.fragment_container,
                    DragonInfoFragment.newInstance(dragon, dragon.id)
                )
                .addToBackStack(null)
                .commit()
            lastClickedElement = null
        }
    }

    override fun onDestroyView() {
        binding?.recyclerView?.adapter = null
        binding = null
        super.onDestroyView()
    }
}
