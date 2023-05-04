package io.devmike01.mapboxautocomplete.models


import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonIgnoreProperties(ignoreUnknown=true)
data class Place(@JsonProperty("type")val placeName: String? =null,
                 @JsonProperty("features")val features: List<Feature> = emptyList()
)

//@JsonIgnoreProperties(ignoreUnknown=true)
//data class Feature(@JsonProperty("place_name") val placeName: String?)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "short_code", "wikidata", "mapbox_id", "text")
class PlaceContext {
    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: String? = null

    @get:JsonProperty("short_code")
    @set:JsonProperty("short_code")
    @JsonProperty("short_code")
    var shortCode: String? = null

    @get:JsonProperty("wikidata")
    @set:JsonProperty("wikidata")
    @JsonProperty("wikidata")
    var wikidata: String? = null

    @get:JsonProperty("mapbox_id")
    @set:JsonProperty("mapbox_id")
    @JsonProperty("mapbox_id")
    var mapboxId: String? = null

    @get:JsonProperty("text")
    @set:JsonProperty("text")
    @JsonProperty("text")
    var text: String? = null

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
@JsonPropertyOrder("type", "coordinates")
class Geometry {
    @get:JsonProperty("type")
    @set:JsonProperty("type")
    @JsonProperty("type")
    var type: String? = null

    @get:JsonProperty("coordinates")
    @set:JsonProperty("coordinates")
    @JsonProperty("coordinates")
    var coordinates: List<Double>? = null

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
    "id",
    "type",
    "place_type",
    "relevance",
    "properties",
    "text",
    "place_name",
    "bbox",
    "center",
    "geometry",
    "context"
)

class Feature {
    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: String? = null

    @get:JsonProperty("type")
    @set:JsonProperty("type")
    @JsonProperty("type")
    var type: String? = null

    @get:JsonProperty("place_type")
    @set:JsonProperty("place_type")
    @JsonProperty("place_type")
    var placeType: List<String>? = null

    @get:JsonProperty("relevance")
    @set:JsonProperty("relevance")
    @JsonProperty("relevance")
    var relevance: Int? = null

    @get:JsonProperty("properties")
    @set:JsonProperty("properties")
    @JsonProperty("properties")
    var properties: Properties? = null

    @get:JsonProperty("text")
    @set:JsonProperty("text")
    @JsonProperty("text")
    var text: String? = null

    @get:JsonProperty("place_name")
    @set:JsonProperty("place_name")
    @JsonProperty("place_name")
    var placeName: String? = null

    @get:JsonProperty("bbox")
    @set:JsonProperty("bbox")
    @JsonProperty("bbox")
    var bbox: List<Double>? = null

    @get:JsonProperty("center")
    @set:JsonProperty("center")
    @JsonProperty("center")
    var center: List<Double>? = null

    @get:JsonProperty("geometry")
    @set:JsonProperty("geometry")
    @JsonProperty("geometry")
    var geometry: Geometry? = null

    @get:JsonProperty("context")
    @set:JsonProperty("context")
    @JsonProperty("context")
    var context: List<PlaceContext>? = null

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
@JsonPropertyOrder("wikidata", "mapbox_id")
class Properties {
    @get:JsonProperty("wikidata")
    @set:JsonProperty("wikidata")
    @JsonProperty("wikidata")
    var wikidata: String? = null

    @get:JsonProperty("mapbox_id")
    @set:JsonProperty("mapbox_id")
    @JsonProperty("mapbox_id")
    var mapboxId: String? = null

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