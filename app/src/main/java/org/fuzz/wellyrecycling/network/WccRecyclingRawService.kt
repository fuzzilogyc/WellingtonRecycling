package org.fuzz.wellyrecycling.network

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WccRecyclingRawService {

    @GET("services/environment-and-waste/rubbish-and-recycling/collection-days/components/collection-search-results")
    fun getStreetCollection(@Query("streetId") streetId : String, @Query("addWeeks") thing: Int? = null) : Deferred<ResponseBody>

}