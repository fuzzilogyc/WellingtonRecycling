package org.fuzz.wellyrecycling.network

import android.content.SharedPreferences
import com.google.gson.Gson
import org.fuzz.wellyrecycling.results.CollectionInformation

class SharedPreferencesLocalStore(private val sharedPreferences: SharedPreferences) : LocalStore {

    override fun getSavedStreetCollections() : String? {
        return sharedPreferences.getString("LAST_SAVED_ADDRESS", null)
    }

    override fun saveCollectionInformation(streetId: String, collectionInformation: CollectionInformation) {
        val json = Gson().toJson(collectionInformation)
        sharedPreferences.edit()
            .putString("LAST_SAVED_ADDRESS", streetId)
            .putString(streetId, json)
            .apply()
    }

    override fun getCollectionInformation(streetId: String): CollectionInformation? {
        val json = sharedPreferences.getString(streetId, null) ?: return null
        return Gson().fromJson(json, CollectionInformation::class.java)
    }

}