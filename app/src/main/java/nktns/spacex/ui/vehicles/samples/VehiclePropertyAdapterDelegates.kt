package nktns.spacex.ui.vehicles.samples

import android.net.Uri
import androidx.core.view.ViewCompat
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import nktns.spacex.R
import nktns.spacex.databinding.VehicleCardPropertyItemBinding
import nktns.spacex.databinding.VehicleComplexPropertyItemBinding
import nktns.spacex.databinding.VehicleImagePropertyItemBinding
import nktns.spacex.databinding.VehicleSimplePropertyItemBinding

object VehiclePropertyAdapterDelegates {
    fun complexPropertyDelegate() =
        adapterDelegateViewBinding<
            ComplexVehicleProperty,
            VehicleProperty,
            VehicleComplexPropertyItemBinding
            >({ inflater, container ->
            VehicleComplexPropertyItemBinding.inflate(
                inflater,
                container,
                false
            )
        }) {
            bind {
                binding.name.text = item.name
                binding.value.text = item.value
            }
        }

    fun imagePropertyDelegate() = adapterDelegateViewBinding<
        ImageVehicleProperty,
        VehicleProperty,
        VehicleImagePropertyItemBinding
        >({ inflater, container ->
        VehicleImagePropertyItemBinding.inflate(
            inflater,
            container,
            false
        )
    }) {
        bind {
            ViewCompat.setTransitionName(binding.image, item.transitionName)
            if (item.imageURI != null) {
                val uriImage = Uri.parse(item.imageURI)
                binding.image.setImageURI(uriImage)
            } else {
                binding.image.hierarchy.setPlaceholderImage(R.drawable.ship_place_holder)
            }
        }
    }

    fun simplePropertyDelegate() = adapterDelegateViewBinding<
        SimpleVehicleProperty, VehicleProperty, VehicleSimplePropertyItemBinding
        >({ inflater, container ->
        VehicleSimplePropertyItemBinding.inflate(
            inflater,
            container,
            false
        )
    }) {
        bind {
            binding.name.text = item.property
        }
    }

    fun cardPropertyDelegate() = adapterDelegateViewBinding<
        CardVehicleProperty, VehicleProperty, VehicleCardPropertyItemBinding
        >({ inflater, container ->
        VehicleCardPropertyItemBinding.inflate(
            inflater,
            container,
            false
        )
    }) {
        bind {
            binding.run {
                firstPropertyValue.text = item.heatShield.material
                secondPropertyValue.text = item.heatShield.tempDegrees.toString()
                thirdPropertyValue.text = item.heatShield.sizeMeters.toString()
            }
        }
    }
}
