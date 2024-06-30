package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Support(
    @JsonProperty("text")
    val text: String,
    @JsonProperty("url")
    val url: String
)
