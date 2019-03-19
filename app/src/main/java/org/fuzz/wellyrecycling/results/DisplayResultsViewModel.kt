package org.fuzz.wellyrecycling.results

import androidx.lifecycle.ViewModel
import org.fuzz.wellyrecycling.search.SearchResult

class DisplayResultsViewModel : ViewModel() {
    var street : SearchResult = SearchResult("", "", "")
}