package api.regres

import api.BaseUrlTestClass
import api.EndPoints.getSingleUserUrl
import api.data.request.EmptyRequest
import api.data.request.ShortUserDataRequest
import api.data.response.*
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.*
import utils.ProjectSettingLiterals.Companion.MAX_TEXT_FIELD_LENGTH
import utils.ProjectSettingLiterals.Companion.PATH_SEPARATOR
import utils.ProjectSettingLiterals.Companion.PICTURE_SUPPORTED_FORMATS
import utils.ProjectSettingLiterals.Companion.SPECIAL_SYMBOLS
import utils.ProjectSettingLiterals.Companion.USER_EMAIL_END
import utils.ResponseCode.Companion.CREATED
import utils.ResponseCode.Companion.NO_CONTENT
import utils.ResponseCode.Companion.SUCCESS
import kotlin.test.assertTrue

/*
*   1. User Email must end with @reqres.in, Status code 200
*   2. User avatar file must start with id of user, Status code 200
*   3. User avatar file must be .jpg, .png or .bmp format, Status code 200
*   4. User's first and second name must start with uppercase, Status code 200
*   5. User's first and second name shouldn't be this special characters (" !№;%:?*()_+={}[]""'':;/|\ "), Status code 200
*   6. Each of User's field: first name, second name, email, must be less 255 symbols length, Status code 200
*   7. Create user: name and job in response same as request, id is positive number, Status code 201
*   8. Update user(PUT): name and job in response same as request, Status code 200
*   9. Update user(PATCH): name and job in response same as request, Status code 200
*   10. Delete user: success delete with empty body, Status code 204
*   11. Delete user: success delete with non-empty body, Status code 204
*/

class SingleUserPage : BaseUrlTestClass() {

    private val existUserId = 2

    override val url: String = getSingleUserUrl(existUserId)
    override val logger: Logger = LoggerFactory.getLogger(SingleUserPage::class.java)

    @Test
    fun userEmailEndWithEmail() {
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
    fun userAvatarFileIsSupportedFormat() {
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
    fun userFistNameSecondNameEmailIsLessThenMaxFieldLength() {
        val user = getUser()

        assertTrue { user.firstName.length <= MAX_TEXT_FIELD_LENGTH }
        assertTrue { user.lastName.length <= MAX_TEXT_FIELD_LENGTH }
        assertTrue { user.email.length <= MAX_TEXT_FIELD_LENGTH }
    }

    @Test
    fun createUserSuccess() {
        val createUserRequestBody = getShortUserDataRequest()

        val createUserResponse =
            postWithResponseEntity<CreateUserResponse, ShortUserDataRequest>(url, createUserRequestBody)

        assertTrue { createUserResponse.responseCode == CREATED }
        assertTrue { createUserResponse.entity.name == createUserRequestBody.name }
        assertTrue { createUserResponse.entity.job == createUserRequestBody.job }
        assertTrue { createUserResponse.entity.id > 0 }
    }

    @Test
    fun updatePutUserSuccess() {
        val updateUserRequestBody = getShortUserDataRequest()

        val createUserResponse =
            putWithResponseEntity<UpdateUserResponse, ShortUserDataRequest>(url, updateUserRequestBody)

        assertTrue { createUserResponse.responseCode == SUCCESS }
        assertTrue { createUserResponse.entity.name == updateUserRequestBody.name }
        assertTrue { createUserResponse.entity.job == updateUserRequestBody.job }
    }

    @Test
    fun updatePatchUserSuccess() {
        val updateUserRequestBody = getShortUserDataRequest()

        val createUserResponse =
            patchWithResponseEntity<UpdateUserResponse, ShortUserDataRequest>(url, updateUserRequestBody)

        assertTrue { createUserResponse.responseCode == SUCCESS }
        assertTrue { createUserResponse.entity.name == updateUserRequestBody.name }
        assertTrue { createUserResponse.entity.job == updateUserRequestBody.job }
    }

    @Test
    fun deleteUserSuccessWithEmptyBody() {
        val user = getUser()
        val deleteUserResponseCode = deleteEntity<UserData>(url, user)

        assertTrue { deleteUserResponseCode == NO_CONTENT }
    }

    @Test
    fun deleteUserSuccessWithNonEmptyBody() {
        val deleteUserResponseCode = deleteEntity<EmptyRequest>(url, EmptyRequest())

        assertTrue { deleteUserResponseCode == NO_CONTENT }
    }

    private fun getUser(): UserData {
        logger.info { "Trying to get user data from Web" }
        val response = getEntity<SingleUserResponse>(url)
        assertTrue { response.responseCode == SUCCESS }
        logger.info { "User date got from Web \n" }

        return response.entity.data
    }

    private fun getShortUserDataRequest(): ShortUserDataRequest {
        val name = "Petr"
        val job = "QA"

        return ShortUserDataRequest(name, job)
    }
}
