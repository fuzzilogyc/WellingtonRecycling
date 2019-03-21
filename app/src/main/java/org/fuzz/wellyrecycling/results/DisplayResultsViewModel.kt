package org.fuzz.wellyrecycling.results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.fuzz.wellyrecycling.network.WccRecyclingRepository
import org.fuzz.wellyrecycling.search.SearchResult

class DisplayResultsViewModel(private val wccRecyclingRepository: WccRecyclingRepository) : ViewModel() {

    val streetCollectionResult : MutableLiveData<String> = MutableLiveData()

    var street : SearchResult = SearchResult("", "", "")
    val isLoadingVisible = street.key.isEmpty()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getStreetCollection(streetId: String) {
        uiScope.launch {
            streetCollectionResult.value = wccRecyclingRepository.getStreetCollection(streetId)
        }
    }
}