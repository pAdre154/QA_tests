package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserResponse(
    @JsonProperty("createdAt")
    val createdAt: String,
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("job")
    val job: String,
    @JsonProperty("name")
    val name: String
)