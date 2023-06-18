package io.devmike01.compose.repo

import retrofit2.http.GET
import retrofit2.http.Query

// geocoding/v5/mapbox.places

interface MapboxService {


    /**
     * @Read more about these parameters from mapbox website:
     * https://docs.mapbox.com/api/search/search-box/
     * @param language : The ISO language code to be returned. If not provided,
     * the default is English.
     * @param: placeName: The search query
     * @param limit: The number of results to return, up to 10.
     * @param latLongProximity: Bias the response to favor results that are closer to a specific
     * location. Provide either ip to get results closest
     * to the user's IP location or provide two comma-separated coordinates in longitude,
     * latitude order. If not provided, the default is IP proximity. When both proximity and origin
     * are provided, origin is interpreted as the target of a route, while proximity indicates the
     * current user location.
     * @param origin: 	The location from which to calculate distance, provided as two
     * comma-separated coordinates in longitude,latitude order. When both proximity and origin are
     * provided, origin is interpreted as the target of a route, while proximity indicates the
     * current user location. This parameter is required for distance-to-target estimates,
     * but may incur additional latency.
     * @param bbox: Limit results to only those contained within the supplied bounding box.
     * Bounding boxes should be supplied as four numbers separated by commas, in minimum
     * longitude,minimum latitude,maximum longitude,maximum latitude order. The bounding box
     * cannot cross the 180th meridian.
     * @param country An ISO 3166 alpha 2 country code.
     *
     *  @param types: Limit results to one or more types of features, provided as a comma-separated
     *  list. Pass one or more of the type names as a comma separated list. If no types are specified,
     *  all possible types may be returned. Available types are: country, region, postcode,
     *  district, place, city, locality, neighborhood, street, and address. See the Administrative
     *  unit types section for details about these types.
     *  @param radius: Radius for the area to search within around a point.
     *  @param user_id: A customer provided user id.
     */

    @GET(MapboxEndpoints.SEARCH_SUGGEST)
    suspend fun getPlace(@Query("q") placeName: String,
                         @Query("language") language: String,
                         @Query("limit")limit: Int,
                         @Query("proximity") latLongProximity: String,
                         @Query("origin")origin: String,
                         @Query("bbox")bbox: String,
                         @Query("country")country: String,
                         @Query("types")types: String,
                         @Query("radius")radius: String,
                         @Query("user_id")userId: String) : SuggestionResponse


}