package org.fuzz.wellyrecycling.network

class WccRecyclingRepositoryImpl(private val wccRecyclingService: WccRecyclingService) : WccRecyclingRepository {

    override suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO {
        return wccRecyclingService.getSearchResults(SearchRequestBody(searchTerm)).await()
    }

}