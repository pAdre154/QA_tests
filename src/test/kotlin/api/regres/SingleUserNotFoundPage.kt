package api.regres

import api.BaseUrlTestClass
import api.EndPoints.Companion.SINGLE_USER_NOT_FOUND_PAGE
import api.data.EmptyResponse
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
    override val url: String = SINGLE_USER_NOT_FOUND_PAGE
    override val logger: Logger = LoggerFactory.getLogger(SingleUserNotFoundPage::class.java)

    @Test
    fun singleUserNotFound() {
        val response = getEntity<EmptyResponse>(url)

        assertTrue { response.responseCode == NOT_FOUND }
    }
}