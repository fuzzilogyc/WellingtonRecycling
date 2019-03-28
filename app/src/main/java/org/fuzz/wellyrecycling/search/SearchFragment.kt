package org.fuzz.wellyrecycling.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import org.apache.commons.lang3.StringUtils
import org.fuzz.wellyrecycling.MainActivity
import org.fuzz.wellyrecycling.R
import org.fuzz.wellyrecycling.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(), SearchResultsAdapter.OnClickListener {

    val viewModel : SearchViewModel by viewModel()

    private lateinit var activity: MainActivity

    private lateinit var binding: FragmentSearchBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = SearchResultsAdapter(this)
        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        binding.viewModel = viewModel
        setupViews()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onItemClick(item: StreetInfo) {
        viewModel.onItemClick(item)
        activity.goToDisplay()
    }

    private fun setupViews() {
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val searchTerm = editable.toString()
                if (StringUtils.isNotEmpty(searchTerm) && searchTerm.length > 2) {
                    viewModel.getSearchResults(searchTerm)
                }
            }
        })
    }

}