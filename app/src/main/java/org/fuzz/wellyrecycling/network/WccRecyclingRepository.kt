package org.fuzz.wellyrecycling.network

import org.fuzz.wellyrecycling.results.CollectionInformation

interface WccRecyclingRepository {
    suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO
    suspend fun getStreetCollection(streetId: String) : CollectionInformation
}