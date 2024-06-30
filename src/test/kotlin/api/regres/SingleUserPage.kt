package api.regres

import api.BaseUrlTestClass
import api.EndPoints
import api.data.response.SingleUserResponse
import api.data.response.UserData
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.ProjectSettingLiterals.Companion.MAX_TEXT_FIELD_LENGTH
import utils.ProjectSettingLiterals.Companion.PATH_SEPARATOR
import utils.ProjectSettingLiterals.Companion.PICTURE_SUPPORTED_FORMATS
import utils.ProjectSettingLiterals.Companion.SPECIAL_SYMBOLS
import utils.ProjectSettingLiterals.Companion.USER_EMAIL_END
import utils.ResponseCode.Companion.SUCCESS
import utils.endsWithOneOf
import utils.getEntity
import kotlin.test.assertTrue

/*
*   1. User Email must end with @reqres.in
*   2. User avatar file must start with id of user
*   3. User avatar file must be .jpg, .png or .bmp format
*   4. User's first and second name must start with uppercase
*   5. User's first and second name shouldn't be this special characters (" !№;%:?*()_+={}[]""'':;/|\ ")
*   6. Each of User's field: first name, second name, email, must be less 255 symbols length
*/

class SingleUserPage : BaseUrlTestClass() {

    override val url = EndPoints.SINGLE_USER_PAGE
    override val logger: Logger = LoggerFactory.getLogger(SingleUserPage::class.java)


    @Test
    fun userEmailEnd() {
        val user = getUser()

        assertTrue { user.email.endsWith(USER_EMAIL_END) }
    }

    @Test
    fun userAvatarFileNameStartsWithUserId() {
        val user = getUser()
        val userId = user.id
        val avatarName = user.avatar.split(PATH_SEPARATOR).last()

        assertTrue { avatarName.startsWith(userId.toString()) }
    }

    @Test
    fun userAvatarFileIsCorrectFormat() {
        val user = getUser()
        val avatarName = user.avatar.split(PATH_SEPARATOR).last()

        assertTrue { avatarName.endsWithOneOf(PICTURE_SUPPORTED_FORMATS) }
    }

    @Test
    fun userFirstAndLastNamesStartUppercase() {
        val user = getUser()

        assertTrue { user.firstName[0].isUpperCase() }
        assertTrue { user.lastName[0].isUpperCase() }
    }

    @Test
    fun userFirstAndLastNamesWithoutSpecialSymbols() {
        val user = getUser()

        assertTrue { user.firstName.indexOfAny(SPECIAL_SYMBOLS.toCharArray()) == -1 }
        assertTrue { user.lastName.indexOfAny(SPECIAL_SYMBOLS.toCharArray()) == -1 }
    }

    @Test
    fun userFistNameSecondNameEmailIsCorrectLength() {
        val user = getUser()

        assertTrue { user.firstName.length <= MAX_TEXT_FIELD_LENGTH }
        assertTrue { user.lastName.length <= MAX_TEXT_FIELD_LENGTH }
        assertTrue { user.email.length <= MAX_TEXT_FIELD_LENGTH }
    }

    private fun getUser(): UserData {
        logger.info { "Trying to get user data from Web" }
        val response = getEntity<SingleUserResponse>(url)
        assertTrue { response.responseCode == SUCCESS }
        logger.info { "User date got from Web \n" }

        return response.entity.data
    }
}
