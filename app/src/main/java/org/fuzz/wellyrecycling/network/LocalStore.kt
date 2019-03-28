package org.fuzz.wellyrecycling.network

import org.fuzz.wellyrecycling.results.CollectionInformation
import org.fuzz.wellyrecycling.search.StreetInfo

interface LocalStore {
    suspend fun saveCollectionInformation(streetId: String, collectionInformation: CollectionInformation)
    suspend fun getCollectionInformation(streetId: String) : CollectionInformation?
    suspend fun getSavedStreetCollections(): StreetInfo?
    suspend fun putSavedStreetCollections(streetId: String, streetInfo: StreetInfo)
}