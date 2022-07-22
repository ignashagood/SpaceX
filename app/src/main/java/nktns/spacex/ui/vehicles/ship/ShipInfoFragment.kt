package nktns.spacex.ui.vehicles.ship

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import nktns.spacex.R
import nktns.spacex.data.database.Ship
import nktns.spacex.data.vehicles.ships.ShipNetworkModel
import nktns.spacex.databinding.ShipFragmentBinding
import nktns.spacex.ui.vehicles.samples.ComplexVehicleProperty
import nktns.spacex.ui.vehicles.samples.ImageVehicleProperty
import nktns.spacex.ui.vehicles.samples.VehicleProperty
import nktns.spacex.ui.vehicles.samples.VehiclePropertyAdapterDelegates

class ShipInfoFragment : Fragment() {
    private var binding: ShipFragmentBinding? = null
    private val adapter = ListDelegationAdapter(
        VehiclePropertyAdapterDelegates.complexPropertyDelegate(),
        VehiclePropertyAdapterDelegates.imagePropertyDelegate()
    )

    companion object {
        private const val SHIP = "ship"
        private const val TRANSITION_NAME = "transition_name"
        fun newInstance(ship: Ship, transitionName: String): ShipInfoFragment {
            return ShipInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(SHIP, ship)
                    putString(TRANSITION_NAME, transitionName)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ShipFragmentBinding.inflate(inflater, container, false).run {
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.recycler_view_divider))
        recyclerView.addItemDecoration(dividerItemDecoration)
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        val ship = requireArguments().getParcelable<ShipNetworkModel>(SHIP)
        binding?.run {
            shipName.text = ship?.name
            backButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        adapter.apply {
            ship?.apply {
                requireArguments().getString(ShipInfoFragment.TRANSITION_NAME)?.let {
                    val propertyList: List<VehicleProperty> = listOf(
                        ImageVehicleProperty(
                            ship.image,
                            it
                        ),
                        ComplexVehicleProperty("Type", type),
                        ComplexVehicleProperty("Model", model ?: ""),
                        ComplexVehicleProperty("Mass", "$massKg kg"),
                        ComplexVehicleProperty("Speed", "$speedKn kn"),
                        //ComplexShipProperty("Year built", "$yearBuilt"),
                        ComplexVehicleProperty("Home port", homePort ?: "no info"),
                        ComplexVehicleProperty("Status", status ?: "no info"),
                        ComplexVehicleProperty("MMSI", "$mmsi"),
                    )
                    items = propertyList
                }
            }
            notifyDataSetChanged()
        }
        (view.parent as? ViewGroup)?.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }
}
