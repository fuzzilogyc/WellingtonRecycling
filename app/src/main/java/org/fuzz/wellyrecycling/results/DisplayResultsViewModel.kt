package org.fuzz.wellyrecycling.results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.fuzz.wellyrecycling.network.WccRecyclingRepository
import org.fuzz.wellyrecycling.search.SearchResult

class DisplayResultsViewModel(private val wccRecyclingRepository: WccRecyclingRepository) : ViewModel() {

    val collectionInformation : MutableLiveData<CollectionInformation> = MutableLiveData()
    var street : SearchResult = SearchResult("", "", "")
    val isLoadingVisible : Boolean
            get() = collectionInformation.value != null

    fun getStreetCollection(streetId: String) {
        viewModelScope.launch {
            collectionInformation.value = wccRecyclingRepository.getStreetCollection(streetId)
        }
    }
}