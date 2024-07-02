package api.regres

import api.BaseUrlTestClass
import api.EndPoints
import api.data.response.ResourceData
import api.data.response.SingleResourceResponse
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.ResponseCode.Companion.SUCCESS
import utils.getEntity
import java.util.*
import kotlin.test.Test
import kotlin.test.assertTrue

/*
*   1) Resource year of production equals format: YYYY. It's positive value, it less or equal to current year, status code 200
*   2) Color value come in Hex format, status code 200
*   3) Pantone color equals format: NN-NNNN, status code 200
*   4) Name contains Latin characters and may contain spaces, status code 200
*/

class SingleResourcePage : BaseUrlTestClass() {

    private val existResourceId = 2

    override val url: String = EndPoints.getSingleResourceUrl(existResourceId)
    override val logger: Logger = LoggerFactory.getLogger(SingleResourcePage::class.java)

    @Test
    fun resourceYearOfProductionWithCorrectFormat() {
        val lengthOfYearFormatSymbols = 4
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val resourceYear = getResource().year

        assertTrue { resourceYear.toString().length == lengthOfYearFormatSymbols }
        assertTrue { resourceYear <= currentYear }
        assertTrue { resourceYear > 0 }
    }

    @Test
    fun resourceColorInHexFormat() {
        val hexPattern = "^#[0-9a-fA-F]{6}\$".toRegex()

        val resourceColor = getResource().color

        assertTrue { hexPattern.containsMatchIn(resourceColor) }
    }

    @Test
    fun resourcePantoneColorInPantoneFormat() {
        val pantonePattern = "^[0-9]{2}-[0-9]{4}$".toRegex()

        val qwe = getResource().pantoneValue.toRegex()

        val resourcePantoneColor = getResource().pantoneValue

        assertTrue { pantonePattern.containsMatchIn(resourcePantoneColor) }
    }

    @Test
    fun resourceNameInContainLatinAndSpaces() {
        val namePattern = "[a-zA-Z\\s]".toRegex()

        val resourceName = getResource().name

        assertTrue { namePattern.containsMatchIn(resourceName) }
    }

    private fun getResource(): ResourceData {
        logger.info { "Trying to get resource data from Web" }
        val response = getEntity<SingleResourceResponse>(url)
        assertTrue { response.responseCode == SUCCESS }
        logger.info { "Resource date got from Web \n" }

        return response.entity.data
    }
}