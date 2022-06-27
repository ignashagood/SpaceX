package nktns.spacex.vehicles

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nktns.spacex.R
import nktns.spacex.data.RocketListItem
import nktns.spacex.databinding.VehicleItemBinding

class VehiclesAdapter : RecyclerView.Adapter<VehiclesAdapter.VehicleHolder>() {

    var rocketList: List<RocketListItem> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun initList(newTaskList: List<RocketListItem>) {
        rocketList = newTaskList
        notifyDataSetChanged()
    }

    fun updateList(newTaskList: List<RocketListItem>, diffResult: DiffUtil.DiffResult) {
        rocketList = newTaskList
        diffResult.dispatchUpdatesTo(this)
    }

    class VehicleHolder(val binding: VehicleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehicleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        val binding = VehicleItemBinding.bind(view)
        return VehicleHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) {
        val rocket = rocketList[position]
        holder.binding.textView.text = rocket.name
    }

    override fun getItemCount(): Int {
        return rocketList.size
    }
}
