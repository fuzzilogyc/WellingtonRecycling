package org.fuzz.wellyrecycling.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StreetInfo(val key : String, val street : String, val suburb: String) : Parcelable