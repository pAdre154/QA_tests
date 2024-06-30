package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserData(
    @JsonProperty("avatar")
    val avatar: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("last_name")
    val lastName: String,
)