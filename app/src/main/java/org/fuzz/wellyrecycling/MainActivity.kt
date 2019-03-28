package org.fuzz.wellyrecycling

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import org.fuzz.wellyrecycling.empty.EmptyFragment
import org.fuzz.wellyrecycling.results.DisplayResultFragment
import org.fuzz.wellyrecycling.search.SearchFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : FragmentActivity() {

    val viewModel : MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.observeAppState().observe(this, Observer {
            when(it) {
                AppState.EMPTY ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EmptyFragment())
                        .commit()
                AppState.SAVED ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, DisplayResultFragment())
                        .commit()
                AppState.SEARCH ->
                    // Do nothing for now
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment())
                        .commit()
            }
        })

        viewModel.collectionStreetId.observe(this, Observer {
            val fragment = DisplayResultFragment()
            val args = Bundle()
            args.putString("streetId", it)
            fragment.arguments = args
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        })
    }

    fun goToSearch() {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
            R.anim.card_flip_right_in, R.anim.card_flip_right_out,
            R.anim.card_flip_left_in, R.anim.card_flip_left_out
        )
        ft.replace(R.id.container, SearchFragment()).addToBackStack("search")
        ft.commit()
    }

    fun goToDisplay() {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
            R.anim.card_flip_left_in, R.anim.card_flip_left_out,
            R.anim.card_flip_right_in, R.anim.card_flip_right_out
        )
        val fragment = DisplayResultFragment()
        ft.replace(R.id.container, fragment)
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
