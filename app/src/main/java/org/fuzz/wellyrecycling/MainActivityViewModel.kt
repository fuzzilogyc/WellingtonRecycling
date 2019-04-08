package org.fuzz.wellyrecycling

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.fuzz.wellyrecycling.network.WccRecyclingRepository

class MainActivityViewModel(private val repo: WccRecyclingRepository) : ViewModel() {

    private lateinit var appState : MutableLiveData<AppState>
    val collectionStreetId : MutableLiveData<String> = MutableLiveData()

    fun observeAppState() : MutableLiveData<AppState> {
        appState = MutableLiveData()

        viewModelScope.launch {
            val savedStreetInfo = repo.getSavedStreetInfo()
            if (savedStreetInfo != null) {
                appState.value = AppState.SAVED
                collectionStreetId.value = savedStreetInfo.key
            } else {
                appState.value = AppState.SEARCH
            }
        }

        return appState
    }

}

enum class AppState {
    SAVED, SEARCH
}