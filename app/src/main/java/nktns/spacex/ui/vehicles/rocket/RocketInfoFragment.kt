package nktns.spacex.ui.vehicles.rocket

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
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.vehicles.rockets.RocketNetworkModel
import nktns.spacex.databinding.RocketFragmentBinding
import nktns.spacex.ui.vehicles.samples.ComplexVehicleProperty
import nktns.spacex.ui.vehicles.samples.ImageVehicleProperty
import nktns.spacex.ui.vehicles.samples.SimpleVehicleProperty
import nktns.spacex.ui.vehicles.samples.VehicleProperty
import nktns.spacex.ui.vehicles.samples.VehiclePropertyAdapterDelegates

class RocketInfoFragment : Fragment() {

    private var binding: RocketFragmentBinding? = null
    private val adapter = ListDelegationAdapter(
        VehiclePropertyAdapterDelegates.complexPropertyDelegate(),
        VehiclePropertyAdapterDelegates.simplePropertyDelegate(),
        VehiclePropertyAdapterDelegates.imagePropertyDelegate()
    )

    companion object {
        private const val ROCKET = "rocket"
        private const val TRANSITION_NAME = "transition_name"
        fun newInstance(rocket: Rocket, transitionName: String): RocketInfoFragment {
            return RocketInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ROCKET, rocket)
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
    ): View = RocketFragmentBinding.inflate(inflater, container, false).run {
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        val rocket = requireArguments().getParcelable<RocketNetworkModel>(ROCKET)
        binding?.run {
            rocketName.text = rocket?.name
            backButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        adapter.apply {
            rocket?.apply {
                requireArguments().getString(TRANSITION_NAME)?.let {
                    val propertyList: List<VehicleProperty> = listOf(
                        ImageVehicleProperty(
                            rocket.flickrImages[0],
                            it
                        ),
                        SimpleVehicleProperty(description),
                        ComplexVehicleProperty("Company", company),
                        ComplexVehicleProperty("Country", country),
                        ComplexVehicleProperty("Mass", "${mass.kg} kg"),
                        ComplexVehicleProperty("Height", "${height.meters} m"),
                        ComplexVehicleProperty("Diameter", "${diameter.meters} m"),
                        ComplexVehicleProperty("First flight", firstFlight),
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
