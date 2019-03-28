package org.fuzz.wellyrecycling.results

import org.fuzz.wellyrecycling.search.StreetInfo

data class CollectionInformation(val streetInfo: StreetInfo, val nextCollectionDate: String, val collectionType: String)