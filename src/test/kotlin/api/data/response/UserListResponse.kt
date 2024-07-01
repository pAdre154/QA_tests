package api.data.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserListResponse(
    @JsonProperty("page")
    val currentPageNumber: Int,
    @JsonProperty("per_page")
    val userCountAtThisPage: Int,
    @JsonProperty("total")
    val totalUsersCount: Int,
    @JsonProperty("total_pages")
    val totalPagesCount: Int,
    @JsonProperty("data")
    val pageUsers: List<UserData>,
    @JsonProperty("support")
    val support: Support,
)