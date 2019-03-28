package org.fuzz.wellyrecycling.network

import org.fuzz.wellyrecycling.results.CollectionInformation
import org.fuzz.wellyrecycling.search.StreetInfo

class WccRecyclingRepositoryImpl(private val wccRecyclingJSONService: WccRecyclingJSONService,
                                 private val wccRecyclingRawService: WccRecyclingRawService,
                                 private val localStore: LocalStore) : WccRecyclingRepository {

    override suspend fun getSearchResults(searchTerm: String) : RecyclingSearchResultsDTO {
        return wccRecyclingJSONService.getSearchResults(SearchRequestBody(searchTerm)).await()
    }

    override suspend fun getStreetCollectionFromNetwork(streetInfo: StreetInfo, weeksToLookAhead: Int) : CollectionInformation {
        val responseBody = wccRecyclingRawService.getStreetCollection(streetInfo.key, weeksToLookAhead).await()
        val responseString = responseBody.string()
        var nextCollectionDate = responseString.substring(
            responseString.indexOf("<p class=\"collection-date\">") + 27,
            responseString.indexOf(" (out before")).trim()
        if (nextCollectionDate.startsWith("<a id=\"content")) {
            nextCollectionDate = responseString.substring(
                responseString.indexOf("addWeeks=" + (weeksToLookAhead - 1) + "\"></a>") + 16,
                responseString.indexOf(" (out before"))
        }
        val collectionType =
            if (responseString.indexOf("Glass crate") == -1) {
                "glass"
            } else {
                "recycling"
            }

        val collectionInformationFromNetwork = CollectionInformation(streetInfo, nextCollectionDate.trim(), collectionType)

        return collectionInformationFromNetwork.also {
            localStore.saveCollectionInformation(streetInfo.key, it)
        }

    }

    override suspend fun getSavedStreetInfo() : StreetInfo? {
        return localStore.getSavedStreetCollections()
    }

    override suspend fun getStreetCollectionFromLocal(streetId: String) : CollectionInformation? {
        return localStore.getCollectionInformation(streetId)
    }

    override suspend fun saveSelectedStreetInfo(streetId: String, streetInfo: StreetInfo) {
        return localStore.putSavedStreetCollections(streetId, streetInfo)
    }

}