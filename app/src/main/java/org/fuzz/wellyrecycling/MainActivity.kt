package org.fuzz.wellyrecycling

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentActivity
import org.fuzz.wellyrecycling.results.DisplayResultFragment
import org.fuzz.wellyrecycling.search.SearchFragment
import org.fuzz.wellyrecycling.search.SearchResult

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DisplayResultFragment())
                .commit()
        }
    }

    fun goToSearch() {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
            R.anim.card_flip_right_in, R.anim.card_flip_right_out,
            R.anim.card_flip_left_in, R.anim.card_flip_left_out
        )
        ft.replace(R.id.container, SearchFragment())
        ft.commit()
    }

    fun goToDisplay(street: SearchResult) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
            R.anim.card_flip_left_in, R.anim.card_flip_left_out,
            R.anim.card_flip_right_in, R.anim.card_flip_right_out
        )
        val fragment = DisplayResultFragment()
        val args = Bundle()
        args.putParcelable("street", street);
        fragment.arguments = args
        ft.replace(R.id.container, fragment)
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
