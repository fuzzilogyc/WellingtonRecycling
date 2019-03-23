package org.fuzz.wellyrecycling.network

import org.fuzz.wellyrecycling.results.CollectionInformation

class WccRecyclingRepositoryImpl(private val wccRecyclingJSONService: WccRecyclingJSONService,
                                 private val wccRecyclingRawService: WccRecyclingRawService) : WccRecyclingRepository {

    override suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO {
        return wccRecyclingJSONService.getSearchResults(SearchRequestBody(searchTerm)).await()
    }

    override suspend fun getStreetCollection(streetId: String, weeksToLookAhead: Int) : CollectionInformation {
        val responseBody = wccRecyclingRawService.getStreetCollection(streetId,
            if (weeksToLookAhead > 0) {
                weeksToLookAhead
            } else {
                null
            }).await()
        val responseString = responseBody.string()
        val nextCollectionDate = responseString.substring(
            responseString.indexOf("<p class=\"collection-date\">") + 27,
            responseString.indexOf(" (out before"))
        val collectionType =
            if (responseString.indexOf("Glass crate") == -1) {
                "glass"
            } else {
                "recycling"
            }

        return CollectionInformation(nextCollectionDate.trim(), collectionType)
    }

}