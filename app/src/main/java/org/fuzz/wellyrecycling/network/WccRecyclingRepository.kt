package org.fuzz.wellyrecycling.network

import org.fuzz.wellyrecycling.results.CollectionInformation
import org.fuzz.wellyrecycling.search.StreetInfo

interface WccRecyclingRepository {
    suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO
    suspend fun getStreetCollectionFromNetwork(street: StreetInfo, weeksToLookAhead: Int = 0) : CollectionInformation

    suspend fun getStreetCollectionFromLocal(streetId: String): CollectionInformation?
    suspend fun getSavedStreetInfo(): StreetInfo?
    suspend fun saveSelectedStreetInfo(streetId: String, streetInfo: StreetInfo)
}