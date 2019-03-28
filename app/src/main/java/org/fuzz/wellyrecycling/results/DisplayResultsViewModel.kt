package org.fuzz.wellyrecycling.results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.fuzz.wellyrecycling.network.WccRecyclingRepository
import org.fuzz.wellyrecycling.search.StreetInfo

class DisplayResultsViewModel(private val wccRecyclingRepository: WccRecyclingRepository) : ViewModel() {

    val collectionInformation : MutableLiveData<CollectionInformation> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private var streetInformation : StreetInfo = StreetInfo("", "", "")
    private var weeksToAdd = 0

    fun checkForSavedStreetCollection() {
        viewModelScope.launch {
            val streetId = wccRecyclingRepository.getSavedStreetCollections()
            if (streetId != null) {
                collectionInformation.value = wccRecyclingRepository.getStreetCollectionFromLocal(streetId)
                streetInformation = collectionInformation.value!!.streetInfo
                refreshFromNetwork()
            } else {
                // TODO what to do if we get to the display results screen and there were no saved steet collections?
            }
        }
    }

    fun refreshFromNetwork() {
        isLoading.value = true
        viewModelScope.launch {
            if (streetInformation.key.isNotEmpty()) {
                collectionInformation.value = wccRecyclingRepository.getStreetCollectionFromNetwork(streetInformation, weeksToAdd)
                isLoading.value = false
            } else {
                // shouldn't be reachable?
            }
        }
    }

    fun previousClicked() {
        if (weeksToAdd > 0) {
            weeksToAdd--
            refreshFromNetwork()
        }
    }

    fun nextClicked() {
        weeksToAdd++
        refreshFromNetwork()
    }

}