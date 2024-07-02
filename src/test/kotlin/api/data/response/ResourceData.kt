package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResourceData(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("year")
    val year: Int,
    @JsonProperty("color")
    val color: String,
    @JsonProperty("pantone_value")
    val pantoneValue: String,
)
