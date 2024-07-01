package api.regres

import api.BaseUrlTestClass
import api.EndPoints.getSingleUserUrl
import api.data.response.EmptyResponse
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.ResponseCode.Companion.NOT_FOUND
import utils.getEntity
import kotlin.test.assertTrue

/*
*   1. User not found, Status code 404,
*/

class SingleUserNotFoundPage : BaseUrlTestClass() {
    private val nonExistUserId = 23

    override val url: String = getSingleUserUrl(nonExistUserId)
    override val logger: Logger = LoggerFactory.getLogger(SingleUserNotFoundPage::class.java)

    @Test
    fun singleUserNotFound() {
        val response = getEntity<EmptyResponse>(url)

        assertTrue { response.responseCode == NOT_FOUND }
    }
}
