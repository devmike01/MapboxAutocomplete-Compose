package io.devmike01.mapboxautocomplete.repo

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("country", "region", "postcode", "place", "neighborhood", "street")
class Context {
    @get:JsonProperty("country")
    @set:JsonProperty("country")
    @JsonProperty("country")
    var country: Country? = null

    @get:JsonProperty("region")
    @set:JsonProperty("region")
    @JsonProperty("region")
    var region: Region? = null

    @get:JsonProperty("postcode")
    @set:JsonProperty("postcode")
    @JsonProperty("postcode")
    var postcode: Postcode? = null

    @get:JsonProperty("place")
    @set:JsonProperty("place")
    @JsonProperty("place")
    var place: Place? = null

    @get:JsonProperty("neighborhood")
    @set:JsonProperty("neighborhood")
    @JsonProperty("neighborhood")
    var neighborhood: Neighborhood? = null

    @get:JsonProperty("street")
    @set:JsonProperty("street")
    @JsonProperty("street")
    var street: Street? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name", "country_code", "country_code_alpha_3")

class Country {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @get:JsonProperty("country_code")
    @set:JsonProperty("country_code")
    @JsonProperty("country_code")
    var countryCode: String? = null

    @get:JsonProperty("country_code_alpha_3")
    @set:JsonProperty("country_code_alpha_3")
    @JsonProperty("country_code_alpha_3")
    var countryCodeAlpha3: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("safegraph", "foursquare")

class ExternalIds {
    @get:JsonProperty("safegraph")
    @set:JsonProperty("safegraph")
    @JsonProperty("safegraph")
    var safegraph: String? = null

    @get:JsonProperty("foursquare")
    @set:JsonProperty("foursquare")
    @JsonProperty("foursquare")
    var foursquare: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder

class Metadata {
    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name")

class Neighborhood {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name")

class Place {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name")

class Postcode {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name", "region_code", "region_code_full")

class Region {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @get:JsonProperty("region_code")
    @set:JsonProperty("region_code")
    @JsonProperty("region_code")
    var regionCode: String? = null

    @get:JsonProperty("region_code_full")
    @set:JsonProperty("region_code_full")
    @JsonProperty("region_code_full")
    var regionCodeFull: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name")

class Street {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("suggestions", "attribution")
data class SuggestionResponse (
    @JsonProperty("suggestions")
    val suggestions: List<Suggestion>? = null,
    @JsonProperty("attribution")
    val attribution: String? = null

){

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "name",
    "mapbox_id",
    "feature_type",
    "address",
    "full_address",
    "place_formatted",
    "context",
    "language",
    "maki",
    "poi_category",
    "poi_category_ids",
    "external_ids",
    "metadata"
)

data class Suggestion (
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null,

    @get:JsonProperty("mapbox_id")
    @set:JsonProperty("mapbox_id")
    @JsonProperty("mapbox_id")
    var mapboxId: String? = null,

    @get:JsonProperty("feature_type")
    @set:JsonProperty("feature_type")
    @JsonProperty("feature_type")
    var featureType: String? = null,

    @get:JsonProperty("address")
    @set:JsonProperty("address")
    @JsonProperty("address")
    var address: String? = null,

    @get:JsonProperty("full_address")
    @set:JsonProperty("full_address")
    @JsonProperty("full_address")
    var fullAddress: String? = null,

    @get:JsonProperty("place_formatted")
    @set:JsonProperty("place_formatted")
    @JsonProperty("place_formatted")
    var placeFormatted: String? = null,

    @get:JsonProperty("context")
    @set:JsonProperty("context")
    @JsonProperty("context")
    var context: Context? = null,

    @get:JsonProperty("language")
    @set:JsonProperty("language")
    @JsonProperty("language")
    var language: String? = null,

    @get:JsonProperty("maki")
    @set:JsonProperty("maki")
    @JsonProperty("maki")
    var maki: String? = null,

    @get:JsonProperty("poi_category")
    @set:JsonProperty("poi_category")
    @JsonProperty("poi_category")
    var poiCategory: List<String>? = null,

    @get:JsonProperty("poi_category_ids")
    @set:JsonProperty("poi_category_ids")
    @JsonProperty("poi_category_ids")
    var poiCategoryIds: List<String>? = null,

    @get:JsonProperty("external_ids")
    @set:JsonProperty("external_ids")
    @JsonProperty("external_ids")
    var externalIds: ExternalIds? = null,

    @get:JsonProperty("metadata")
    @set:JsonProperty("metadata")
    @JsonProperty("metadata")
    var metadata: Metadata? = null

){

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = LinkedHashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}