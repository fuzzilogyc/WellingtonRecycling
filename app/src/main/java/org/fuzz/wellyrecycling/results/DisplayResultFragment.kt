package org.fuzz.wellyrecycling.results

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_display_results.*
import org.fuzz.wellyrecycling.MainActivity
import org.fuzz.wellyrecycling.R
import org.fuzz.wellyrecycling.databinding.FragmentDisplayResultsBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DisplayResultFragment : Fragment() {

    val viewModel : DisplayResultsViewModel by viewModel()

    private lateinit var activity: MainActivity

    private lateinit var binding: FragmentDisplayResultsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_display_results, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        viewModel.isLoading.observe(this, Observer {
            swipe_layout.isRefreshing = it
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkForSavedStreetCollection()
    }

    private fun setupButtons() {
        swipe_layout.setOnRefreshListener {
            viewModel.refreshFromNetwork()
        }
        prev_button.setOnClickListener { viewModel.previousClicked() }
        next_button.setOnClickListener { viewModel.nextClicked() }
    }

}