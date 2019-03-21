package org.fuzz.wellyrecycling.network

interface WccRecyclingRepository {
    suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO
    suspend fun getStreetCollection(streetId: String) : String
}