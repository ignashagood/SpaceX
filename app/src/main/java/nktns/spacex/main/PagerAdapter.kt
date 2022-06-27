package nktns.spacex.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import nktns.spacex.vehicles.VehiclesFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VehiclesFragment()
            1 -> TODO()
            2 -> TODO()
            3 -> TODO()
            4 -> TODO()
            else -> error("Unexpected position $position")
        }
    }
}
