package io.devmike01.mapboxautocomplete.repo

object MapboxEndpoints {
    //geocoding/v5/mapbox.places
    const val GEOCODING = "/geocoding/v5"
    const val PLACES = "$GEOCODING/mapbox.places"
    const val SEARCH_BOX = "/search/searchbox/v1"
    const val SEARCH_SUGGEST = "$SEARCH_BOX/suggest"

}