package api.regres

import api.BaseUrlTestClass
import api.EndPoints.getUsersListPageUrl
import api.data.response.UserListResponse
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.ResponseCode.Companion.SUCCESS
import utils.getEntity
import kotlin.test.assertTrue

/*
*   1. Page field equals page number in request, status code 200
*   2. Users count list at page match with stated, status code 200
*   4. Next page after last page is empty, status code 200
*/
class UserListPage : BaseUrlTestClass() {

    private val userListPage = 2

    override val url: String = getUsersListPageUrl(userListPage)
    override val logger: Logger = LoggerFactory.getLogger(UserListPage::class.java)

    @Test
    fun pageInResponseLikeNumberOfPageInUrl() {
        val userListResponse = getUserListResponse()

        assertTrue { userListResponse.currentPageNumber == userListPage }
    }

    @Test
    fun usersCountInPageMatchWithStated() {
        val userListResponse = getUserListResponse()

        assertTrue { userListResponse.pageUsers.count() == userListResponse.userCountAtThisPage }
    }

    @Test
    fun responseOfUserListAfterLastPageIsEmpty() {
        val userListResponse = getUserListResponse()
        val lastPageNumber = userListResponse.totalPagesCount

        val pageAfterLastUrl = getUsersListPageUrl(lastPageNumber + 1)
        val response = getUserListResponse(pageAfterLastUrl)

        assertTrue { response.pageUsers.isEmpty() }
    }

    private fun getUserListResponse(
        url: String = this.url
    ): UserListResponse {
        logger.info { "Trying to get user data list from Web" }
        val response = getEntity<UserListResponse>(url)
        assertTrue { response.responseCode == SUCCESS }
        logger.info { "User data list from Web \n" }

        return response.entity
    }
}