package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class SuccessLoginResponse(
    @JsonProperty("token")
    val token: String
)