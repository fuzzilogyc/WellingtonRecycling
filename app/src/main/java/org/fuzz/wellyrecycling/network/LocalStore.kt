package org.fuzz.wellyrecycling.network

import org.fuzz.wellyrecycling.results.CollectionInformation

interface LocalStore {
    fun saveCollectionInformation(streetId: String, token: CollectionInformation)
    fun getCollectionInformation(streetId: String) : CollectionInformation?
    fun getSavedStreetCollections(): String?
}