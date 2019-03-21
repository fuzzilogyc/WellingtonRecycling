package org.fuzz.wellyrecycling.results

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_display_results.*
import org.fuzz.wellyrecycling.MainActivity
import org.fuzz.wellyrecycling.R
import org.fuzz.wellyrecycling.databinding.FragmentDisplayResultsBinding
import org.fuzz.wellyrecycling.search.SearchResult
import org.koin.android.viewmodel.ext.android.viewModel


class DisplayResultFragment : Fragment() {

    val viewModel : DisplayResultsViewModel by viewModel()

    private lateinit var activity: MainActivity

    private lateinit var binding: FragmentDisplayResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.street = it.getParcelable<SearchResult>("street")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_display_results, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setupButtons()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isLoadingVisible) {
            viewModel.getStreetCollection(viewModel.street.key)
            //load url
//            val postData = "streetId=" + URLEncoder.encode(viewModel.street.key, "UTF-8") + "&streetName=" + URLEncoder.encode(
//                (viewModel.street.street + ", " + viewModel.street.suburb),
//                "UTF-8"
//            )
//            webView.webViewClient = WebViewClient()
//            webView.loadUrl("http://wellington.govt.nz/services/environment-and-waste/rubbish-and-recycling/collection-days/components/collection-search-results?streetId=" + viewModel.street.key)
        }
    }

    private fun setupButtons() {
//        previous_week.setOnClickListener { println("Clicked prev") }
//
//        next_week.setOnClickListener { println("Clicked next") }

        search_button.setOnClickListener { activity.goToSearch() }

    }

}