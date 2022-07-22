package nktns.spacex.ui.vehicles.dragon

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
import nktns.spacex.data.database.Dragon
import nktns.spacex.databinding.DragonFragmentBinding
import nktns.spacex.ui.vehicles.samples.ComplexVehicleProperty
import nktns.spacex.ui.vehicles.samples.ImageVehicleProperty
import nktns.spacex.ui.vehicles.samples.SimpleVehicleProperty
import nktns.spacex.ui.vehicles.samples.VehicleProperty
import nktns.spacex.ui.vehicles.samples.VehiclePropertyAdapterDelegates

class DragonInfoFragment : Fragment() {
    private var binding: DragonFragmentBinding? = null
    private val adapter = ListDelegationAdapter(
        VehiclePropertyAdapterDelegates.complexPropertyDelegate(),
        VehiclePropertyAdapterDelegates.imagePropertyDelegate(),
        VehiclePropertyAdapterDelegates.simplePropertyDelegate(),
        VehiclePropertyAdapterDelegates.cardPropertyDelegate()
    )

    companion object {
        private const val DRAGON = "dragon"
        private const val TRANSITION_NAME = "transition_name"
        fun newInstance(dragon: Dragon, transitionName: String): DragonInfoFragment {
            return DragonInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DRAGON, dragon)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = DragonFragmentBinding.inflate(inflater, container, false).run {
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
        val dragon = requireArguments().getParcelable<Dragon>(DRAGON) as Dragon
        binding?.run {
            dragonName.text = dragon.name
            backButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        adapter.apply {
            dragon.apply {
                requireArguments().getString(DragonInfoFragment.TRANSITION_NAME)?.let {
                    val propertyList: List<VehicleProperty> = listOf(
                        ImageVehicleProperty(
                            dragon.image,
                            it
                        ),
                        SimpleVehicleProperty(description),
                        ComplexVehicleProperty("Height", "$height m"),
                        ComplexVehicleProperty("Diameter", "$diameter m"),
                        ComplexVehicleProperty("Launch payload mass", "$launchPayloadMass kg"),
                        ComplexVehicleProperty("Return payload mass", "$returnPayloadMass kg"),
                        ComplexVehicleProperty("Return payload mass", "$trunkVolume m3"),
                        // CardVehicleProperty(heatShield),
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
