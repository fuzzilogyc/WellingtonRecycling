package org.fuzz.wellyrecycling.network

class WccRecyclingRepositoryImpl(private val wccRecyclingJSONService: WccRecyclingJSONService,
                                 private val wccRecyclingRawService: WccRecyclingRawService) : WccRecyclingRepository {

    override suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO {
        return wccRecyclingJSONService.getSearchResults(SearchRequestBody(searchTerm)).await()
    }

    override suspend fun getStreetCollection(streetId: String): String {
        val call = wccRecyclingRawService.getStreetCollection(streetId)
        val response = call.execute()
        return if (response.raw().body().toString() != null) response.raw().body().toString() else ""
    }
}