package org.fuzz.wellyrecycling.network

class WccRecyclingRepositoryImpl(private val wccRecyclingJSONService: WccRecyclingJSONService,
                                 private val wccRecyclingRawService: WccRecyclingRawService) : WccRecyclingRepository {

    override suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO {
        return wccRecyclingJSONService.getSearchResults(SearchRequestBody(searchTerm)).await()
    }

    override suspend fun getStreetCollection(streetId: String): String {
        val responseBody = wccRecyclingRawService.getStreetCollection(streetId).await()
        return responseBody.string() ?: ""
    }
}