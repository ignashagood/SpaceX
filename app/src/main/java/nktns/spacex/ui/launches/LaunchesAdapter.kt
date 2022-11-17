package nktns.spacex.ui.launches

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nktns.spacex.R
import nktns.spacex.databinding.LaunchListItemBinding
import nktns.spacex.ui.LaunchUIModel

class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.LaunchViewHolder>() {

    private var launches: List<LaunchUIModel> = emptyList()

    class LaunchViewHolder(private val binding: LaunchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(launch: LaunchUIModel) = with(binding) {
            name.text = launch.name
            date.text = launch.date
            launchImage.setImageURI(launch.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LaunchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.launch_list_item, parent, false)
        val binding = LaunchListItemBinding.bind(view)
        return LaunchViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount() = launches.size

    @SuppressLint("NotifyDataSetChanged")
    fun initList(newCatalogList: List<LaunchUIModel>) {
        launches = newCatalogList
        notifyDataSetChanged()
    }

    fun updateList(newCatalogList: List<LaunchUIModel>, diffResult: DiffUtil.DiffResult) {
        launches = newCatalogList
        diffResult.dispatchUpdatesTo(this)
    }
}
