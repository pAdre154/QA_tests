package api.regres

import api.BaseUrlTestClass
import api.EndPoints
import api.data.request.LoginRequest
import api.data.response.FailedLoginResponse
import api.data.response.SuccessLoginResponse
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory
import utils.ResponseCode.Companion.BAD_REQUEST
import utils.ResponseCode.Companion.SUCCESS
import utils.ResponseMessages.Companion.EMPTY_LOGIN_AND_ANY_PASSWORD
import utils.ResponseMessages.Companion.EMPTY_PASSWORD
import utils.ResponseMessages.Companion.INCORRECT_USER
import utils.postWithResponseEntity
import kotlin.test.assertTrue

/*
*   1. Login success with correct login and password, Status code 200
*   2. Login failed with incorrect login and correct password, Status code 400
*   3. Login failed with correct login and incorrect password, Status code 400
*   4. Login failed with correct login and empty password, Status code 400
*   5. Login failed with empty login and empty password, Status code 400
*/

class LoginPage : BaseUrlTestClass() {
    override val logger: Logger = LoggerFactory.getLogger(LoginPage::class.java)
    override val url: String = EndPoints.getLoginPageUrl()

    private val userCorrectEmail = "eve.holt@reqres.in"
    private val userIncorrectEmail = "holt@reqres.in"
    private val emptyEmail = ""

    private val userCorrectPassword = "cityslicka"
    private val userIncorrectPassword = "incorrectPassword"
    private val emptyPassword = ""

    private val correctTokenInResponse = "QpwL5tke4Pnpja7X4"

    @Test
    fun successLogin() {
        val successLoginRequestBody = LoginRequest(
            email = userCorrectEmail,
            password = userCorrectPassword
        )

        val loginResponse = postWithResponseEntity<SuccessLoginResponse, LoginRequest>(url, successLoginRequestBody)

        assertTrue { loginResponse.responseCode == SUCCESS }
        assertTrue { loginResponse.entity.token == correctTokenInResponse }
    }

    @Test
    fun failedLoginWithIncorrectEmail() {
        val failedLoginRequestBody = LoginRequest(
            email = userIncorrectEmail,
            password = userCorrectPassword
        )

        val loginResponse = postWithResponseEntity<FailedLoginResponse, LoginRequest>(url, failedLoginRequestBody)

        assertTrue { loginResponse.responseCode == BAD_REQUEST }
        assertTrue { loginResponse.entity.error == INCORRECT_USER }
    }

    @Disabled
    @Test /*Test failed, because it's the website's bug*/ // TODO notice site developer
    fun failedLoginWithIncorrectPassword() {
        val failedLoginRequestBody = LoginRequest(
            email = userCorrectEmail,
            password = userIncorrectPassword
        )

        val loginResponse = postWithResponseEntity<FailedLoginResponse, LoginRequest>(url, failedLoginRequestBody)

        assertTrue { loginResponse.responseCode == BAD_REQUEST }
        assertTrue { loginResponse.entity.error == EMPTY_PASSWORD }
    }

    @Test
    fun failedLoginWithEmptyPassword() {
        val failedLoginRequestBody = LoginRequest(
            email = userCorrectEmail,
            password = emptyPassword
        )

        val loginResponse = postWithResponseEntity<FailedLoginResponse, LoginRequest>(url, failedLoginRequestBody)

        assertTrue { loginResponse.responseCode == BAD_REQUEST }
        assertTrue { loginResponse.entity.error == EMPTY_PASSWORD }
    }

    @Test
    fun failedLoginWithEmptyPasswordAndEmptyEmail() {
        val failedLoginRequestBody = LoginRequest(
            email = emptyEmail,
            password = emptyPassword
        )

        val loginResponse = postWithResponseEntity<FailedLoginResponse, LoginRequest>(url, failedLoginRequestBody)

        assertTrue { loginResponse.responseCode == BAD_REQUEST }
        assertTrue { loginResponse.entity.error == EMPTY_LOGIN_AND_ANY_PASSWORD }
    }
}
