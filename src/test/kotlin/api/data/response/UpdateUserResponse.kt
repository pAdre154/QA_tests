package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUserResponse(
    @JsonProperty("job")
    val job: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("updatedAt")
    val updatedAt: String
)