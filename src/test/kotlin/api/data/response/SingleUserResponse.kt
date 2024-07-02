package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class SingleUserResponse(
    @JsonProperty("data")
    val `data`: UserData,
    @JsonProperty("support")
    val support: Support
)