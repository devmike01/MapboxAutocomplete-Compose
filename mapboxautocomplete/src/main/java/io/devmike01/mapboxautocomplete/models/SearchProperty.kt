package io.devmike01.mapboxautocomplete.models

data class SearchProperty(
    val language: String="en",
    val limit: Int=10,
    val latLongProximity: String="",
    val origin: String="",
    val bbox: String="",
    val country: String="",
    val searchTypes: SearchTypes = SearchTypes.Default,
    val radius: String="",
    val userId: String="")

interface SearchTypes{
    val type: String

    /*
    	Limit results to one or more types of features, provided as a comma-separated list.
    	Pass one or more of the type names as a comma separated list.
    	If no types are specified, all possible types may be returned.
    	Available types are: Country, Region, Postcode, District, Place, City, Locality,
    	Neighborhood, Street, and Address. See the Administrative unit types section for details about these types.
     */
    object Default : SearchTypes{
        override val type: String
            get() = ""
    }

    data class Custom( override val type: String="") : SearchTypes

    object Street : SearchTypes{
        override val type: String
            get() = "street"
    }

    object Country : SearchTypes{
        override val type: String
            get() = "country"
    }
    object Postcode : SearchTypes{
        override val type: String
            get() = "postcode"
    }
    object District : SearchTypes{
        override val type: String
            get() = "district"
    }
    object Place : SearchTypes{
        override val type: String
            get() = "place"
    }
    object City : SearchTypes{
        override val type: String
            get() = "city"
    }
    object Locality : SearchTypes{
        override val type: String
            get() = "locality"
    }
    object Neighborhood : SearchTypes{
        override val type: String
            get() = "neighborhood"
    }
    object Address : SearchTypes{
        override val type: String
            get() = "address"
    }
    object Region : SearchTypes{
        override val type: String
            get() = "region"
    }

}
