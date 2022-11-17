package nktns.spacex.ui.vehicles.vehicleList

import android.net.Uri
import androidx.core.view.ViewCompat
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.Ship
import nktns.spacex.data.database.VehicleModel
import nktns.spacex.databinding.DragonItemBinding
import nktns.spacex.databinding.RocketItemBinding
import nktns.spacex.databinding.ShipItemBinding
import nktns.spacex.util.OnVehicleClickListener

object VehicleAdapterDelegates {

    fun rocketAdapterDelegate(itemClickListener: OnVehicleClickListener) = adapterDelegateViewBinding<Rocket, VehicleModel, RocketItemBinding>(
        { inflater, container -> RocketItemBinding.inflate(inflater, container, false) }
    ) {
        binding.root.setOnClickListener {
            itemClickListener.onItemClickListener(item, it)
        }
        bind {
            ViewCompat.setTransitionName(binding.vehicleImage, item.id)
            val uriImage = Uri.parse(item.image)
            binding.vehicleImage.setImageURI(uriImage)
            binding.textView.text = item.name
        }
    }

    fun shipAdapterDelegate(itemClickListener: OnVehicleClickListener) = adapterDelegateViewBinding<Ship, VehicleModel, ShipItemBinding>(
        { inflater, container -> ShipItemBinding.inflate(inflater, container, false) }
    ) {
        binding.root.setOnClickListener {
            itemClickListener.onItemClickListener(item, it)
        }
        bind {
            ViewCompat.setTransitionName(binding.vehicleImage, item.id)
            item.image?.let {
                val uriImage = Uri.parse(it)
                binding.vehicleImage.setImageURI(uriImage)
            }
            binding.textView.text = item.name
        }
    }

    fun dragonAdapterDelegate(itemClickListener: OnVehicleClickListener) = adapterDelegateViewBinding<Dragon, VehicleModel, DragonItemBinding>(
        { inflater, container -> DragonItemBinding.inflate(inflater, container, false) }
    ) {
        binding.root.setOnClickListener {
            itemClickListener.onItemClickListener(item, it)
        }
        bind {
            ViewCompat.setTransitionName(binding.vehicleImage, item.id)
            val uriImage = Uri.parse(item.image)
            binding.vehicleImage.setImageURI(uriImage)
            binding.textView.text = item.name
        }
    }
}
