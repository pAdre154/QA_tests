package api.regres

import api.BaseUrlTestClass
import api.EndPoints
import api.data.response.EmptyResponse
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.ResponseCode.Companion.NOT_FOUND
import utils.getEntity
import kotlin.test.assertTrue

/*
*   1) Non-existent resource contained an empty body, status code 404
*/

class SingleResourceNotFoundPage : BaseUrlTestClass() {
    private val nonExistResourceId = 23

    override val url: String = EndPoints.getSingleResourceUrl(nonExistResourceId)
    override val logger: Logger = LoggerFactory.getLogger(SingleResourceNotFoundPage::class.java)

    @Test
    fun singleResourceNotFound() {
        val response = getEntity<EmptyResponse>(url)

        assertTrue { response.responseCode == NOT_FOUND }
    }

}