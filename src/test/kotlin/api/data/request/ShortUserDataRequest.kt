package api.data.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ShortUserDataRequest(
    @JsonProperty("job")
    val job: String,
    @JsonProperty("name")
    val name: String
)