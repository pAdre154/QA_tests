package api

object EndPoints {
    private const val USERS_LIST_PAGE = "https://reqres.in/api/users?page="
    private const val SINGLE_USER_PAGE = "https://reqres.in/api/users/"

    private const val LOGIN_PAGE = "https://reqres.in/api/login"

    private const val SINGLE_RESOURCE_PAGE = "https://reqres.in/api/unknown/"

    fun getUsersListPageUrl(pageNumber: Int) = "$USERS_LIST_PAGE$pageNumber"

    fun getSingleUserUrl(userId: Int) = "$SINGLE_USER_PAGE$userId"

    fun getSingleResourceUrl(resourceId: Int) = "$SINGLE_RESOURCE_PAGE$resourceId"

    fun getLoginPageUrl() = LOGIN_PAGE
}
