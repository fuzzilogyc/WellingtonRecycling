package org.fuzz.wellyrecycling.empty

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_display_results.*
import kotlinx.android.synthetic.main.fragment_empty.*
import org.fuzz.wellyrecycling.MainActivity
import org.fuzz.wellyrecycling.R


class EmptyFragment : Fragment() {

    private lateinit var activity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupButtons()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    private fun setupButtons() {
        search_button.setOnClickListener { activity.goToSearch() }
    }

}