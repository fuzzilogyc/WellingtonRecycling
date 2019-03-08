package org.fuzz.wellyrecycling.network

interface WccRecyclingRepository {
    suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO
}