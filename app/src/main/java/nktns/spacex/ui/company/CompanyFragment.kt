package nktns.spacex.ui.company

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import nktns.spacex.databinding.CompanyFragmentBinding
import nktns.spacex.getAppComponent

class CompanyFragment : Fragment() {
    private var binding: CompanyFragmentBinding? = null
    private val viewModel: CompanyVM by viewModels {
        getAppComponent().companyVMFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = CompanyFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    is CompanyState.InitialLoading -> {}
                    is CompanyState.Content -> {
                        applyState(it)
                    }
                }
            }
        }
    }

    private fun applyState(state: CompanyState.Content) {
        binding?.run {
            bind(this, state)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun bind(binding: CompanyFragmentBinding, state: CompanyState.Content) {
        binding.run {
            val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse(state.company.website))
            val twitterIntent = Intent(Intent.ACTION_VIEW, Uri.parse(state.company.twitter))
            name.text = state.company.name
            founder.text = state.company.founder
            val uri = Uri.parse(IMAGE_URI)
            image.setImageURI(uri)
            twitter.setOnClickListener { startActivity(twitterIntent) }
            website.setOnClickListener { startActivity(websiteIntent) }
            founded.text = state.company.founded
            summary.text = state.company.summary
        }
    }
}

const val IMAGE_URI =
    "https://www.nasa.gov/sites/default/files/thumbnails/image/for_press_release.jpg"
