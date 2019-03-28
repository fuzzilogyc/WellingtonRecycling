package org.fuzz.wellyrecycling

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.fuzz.wellyrecycling.network.LocalStore

class MainActivityViewModel(private val localStore: LocalStore) : ViewModel() {

    private lateinit var appState : MutableLiveData<AppState>
    val collectionStreetId : MutableLiveData<String> = MutableLiveData()

    fun observeAppState() : MutableLiveData<AppState> {
        appState = MutableLiveData()

        val savedStreetCollection = localStore.getSavedStreetCollections()
        if (savedStreetCollection != null) {
            appState.value = AppState.SAVED
            collectionStreetId.value = savedStreetCollection
        } else {
            appState.value = AppState.EMPTY
        }

        return appState
    }

}

enum class AppState {
    EMPTY, SAVED, SEARCH
}