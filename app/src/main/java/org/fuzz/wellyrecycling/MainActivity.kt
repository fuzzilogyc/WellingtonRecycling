package org.fuzz.wellyrecycling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.fuzz.wellyrecycling.results.DisplayResultFragment
import org.fuzz.wellyrecycling.search.SearchFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel : MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = ""

        setSupportActionBar(toolbar)

        viewModel.observeAppState().observe(this, Observer {
            when(it) {
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

    fun goToSearch() {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
            R.anim.card_flip_left_in, R.anim.card_flip_left_out,
            R.anim.card_flip_right_in, R.anim.card_flip_right_out
        )
        val fragment = SearchFragment()
        ft.replace(R.id.container, fragment).addToBackStack("")
        ft.commit()
    }

}
