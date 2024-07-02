package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class SingleResourceResponse(
    @JsonProperty("data")
    val data: ResourceData,
    @JsonProperty("support")
    val support: Support
)
