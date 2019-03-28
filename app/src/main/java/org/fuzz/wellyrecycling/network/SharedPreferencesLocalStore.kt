package org.fuzz.wellyrecycling.network

import android.content.SharedPreferences
import com.google.gson.Gson
import org.fuzz.wellyrecycling.results.CollectionInformation
import org.fuzz.wellyrecycling.search.StreetInfo

class SharedPreferencesLocalStore(private val sharedPreferences: SharedPreferences) : LocalStore {

    override suspend fun getSavedStreetCollections() : StreetInfo? {
        val json = sharedPreferences.getString("LAST_SAVED_ADDRESS", null) ?: return null
        return Gson().fromJson(json, StreetInfo::class.java)
    }

    override suspend fun putSavedStreetCollections(streetId: String, streetInfo: StreetInfo) {
        val json = Gson().toJson(streetInfo)
        sharedPreferences.edit()
            .putString("LAST_SAVED_ADDRESS", json)
            .apply()
    }

    override suspend fun saveCollectionInformation(streetId: String, collectionInformation: CollectionInformation) {
        val gson = Gson()
        val json = gson.toJson(collectionInformation)
        sharedPreferences.edit()
            .putString("LAST_SAVED_ADDRESS", gson.toJson(collectionInformation.streetInfo))
            .putString(streetId, json)
            .apply()
    }

    override suspend fun getCollectionInformation(streetId: String): CollectionInformation? {
        val json = sharedPreferences.getString(streetId, null) ?: return null
        return Gson().fromJson(json, CollectionInformation::class.java)
    }

}