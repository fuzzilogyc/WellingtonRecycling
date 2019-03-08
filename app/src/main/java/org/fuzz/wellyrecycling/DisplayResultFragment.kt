package org.fuzz.wellyrecycling

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

/**
 * A placeholder fragment containing a simple view.
 */
class DisplayResultFragment : androidx.fragment.app.Fragment() {

    private lateinit var activity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_display_results, container, false)
        setupButtons(rootView)
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    private fun setupButtons(rootView: View) {
        val prevButton = rootView.findViewById<View>(R.id.previous_week) as ImageButton
        prevButton.setOnClickListener { println("Clicked prev") }

        val nextButton = rootView.findViewById<View>(R.id.next_week) as ImageButton
        nextButton.setOnClickListener { println("Clicked next") }

        val searchButton = rootView.findViewById<View>(R.id.search_button) as Button
        searchButton.setOnClickListener { activity.goToSearch() }

    }

}