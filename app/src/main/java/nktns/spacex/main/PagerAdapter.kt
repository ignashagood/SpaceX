package nktns.spacex.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import nktns.spacex.ui.company.CompanyFragment
import nktns.spacex.ui.launches.LaunchesFragment
import nktns.spacex.ui.vehicles.vehicleList.VehicleListFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VehicleListFragment()
            1 -> CompanyFragment()
            2 -> LaunchesFragment()
            else -> error("Unexpected position $position")
        }
    }
}
