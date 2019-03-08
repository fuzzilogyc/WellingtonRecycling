package org.fuzz.wellyrecycling.network

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface WccRecyclingService {

    @POST("layouts/wcc/GeneralLayout.aspx/GetRubbishCollectionStreets")
    fun getSearchResults(@Body searchRequestBody: SearchRequestBody) : Deferred<RecyclingSearchResultsDTO>

}