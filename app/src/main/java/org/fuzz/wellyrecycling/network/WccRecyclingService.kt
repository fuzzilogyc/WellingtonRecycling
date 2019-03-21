package org.fuzz.wellyrecycling.network

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WccRecyclingService {

    @POST("layouts/wcc/GeneralLayout.aspx/GetRubbishCollectionStreets")
    fun getSearchResults(@Body searchRequestBody: SearchRequestBody) : Deferred<RecyclingSearchResultsDTO>

    @GET("services/environment-and-waste/rubbish-and-recycling/collection-days/components/collection-search-results")
    fun getStreetCollection(@Query("streetId") streetId : String) : Deferred<String>

}