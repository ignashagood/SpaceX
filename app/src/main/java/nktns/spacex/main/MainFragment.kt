package nktns.spacex.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import nktns.spacex.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        pager.adapter = PagerAdapter(childFragmentManager, lifecycle)
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.run {
            TabLayoutMediator(tabs, pager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Vehicles"
                }
            }.attach()
        }
    }
}
