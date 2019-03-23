package org.fuzz.wellyrecycling.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.fuzz.wellyrecycling.network.WccRecyclingRepository

class SearchViewModel(private val wccRecyclingRepository: WccRecyclingRepository) : ViewModel() {

    val searchList : MutableLiveData<List<SearchResult>> = MutableLiveData()

    init {
        searchList.value = mutableListOf()
    }

    fun getSearchResults(searchTerm: String) {
        viewModelScope.launch {
            val dto = wccRecyclingRepository.getSearchResults(searchTerm)
            val results = ArrayList<SearchResult>()
            for (item in dto.d) {
                val result = SearchResult(item.Key, item.Value.split(',')[0], item.Value.split(',')[1])
                results.add(result)
            }
            searchList.value = results
        }
    }
}