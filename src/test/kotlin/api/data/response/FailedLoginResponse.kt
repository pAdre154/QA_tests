package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class FailedLoginResponse(
    @JsonProperty("error")
    val error: String
)