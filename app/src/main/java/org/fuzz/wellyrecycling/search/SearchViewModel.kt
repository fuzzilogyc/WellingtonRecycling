package org.fuzz.wellyrecycling.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.fuzz.wellyrecycling.network.WccRecyclingRepository

class SearchViewModel(private val wccRecyclingRepository: WccRecyclingRepository) : ViewModel() {

    val searchList : MutableLiveData<List<SearchResult>> = MutableLiveData()

    init {
        searchList.value = mutableListOf()
    }

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getSearchResults(searchTerm: String) {
        uiScope.launch {
            val dto = wccRecyclingRepository.getSearchResults(searchTerm)
            val results = ArrayList<SearchResult>()
            if (dto.d == null) {
                return@launch
            }
            for (item in dto.d) {
                if (item.Key == null || item.Value == null) {
                    return@launch
                }
                val result = SearchResult(item.Key, item.Value.split(',')[0], item.Value.split(',')[1])
                results.add(result)
            }
            searchList.value = results
        }
    }
}