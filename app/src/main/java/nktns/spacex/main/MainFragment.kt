package nktns.spacex.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nktns.spacex.R
import nktns.spacex.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = MainFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        pager.adapter = PagerAdapter(childFragmentManager, lifecycle)
        pager.isUserInputEnabled = false
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.run {
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.page_1 -> {
                        pager.currentItem = 0
                        true
                    }
                    R.id.page_2 -> {
                        pager.currentItem = 1
                        true
                    }
//                    R.id.page_3 -> {
//                        pager.currentItem = 2
//                        true
//                    }
                    else -> false
                }
            }
        }
    }

    override fun onDestroyView() {
        binding?.run {
            pager.adapter = null
            binding = null
        }
        super.onDestroyView()
    }
}
