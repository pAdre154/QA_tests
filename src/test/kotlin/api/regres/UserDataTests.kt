package api.regres

import api.BaseUrlTest
import api.EndPoints
import api.data.UserData
import com.google.gson.Gson
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory

/*
*   1. User Email must end with @reqres.in
*   2. User avatar file must start with id of user
*   3. User avatar file must be .jpg, .png or .bmp format
*   4. User's first and second name must start with uppercase
*   5. User's first and second name shouldn't be this special characters (" !"№;%:?*()_+={}[]""'':;/|\ ")
*   6. Each of User's field: first name, second name, email, must be less 255 symbols length
*/
class UserDataTests : BaseUrlTest() {

    override val url = EndPoints.SINGLE_USER_PAGE
    override val logger: Logger = LoggerFactory.getLogger(UserDataTests::class.java)


    @Test
    fun userEmailEnd() {
        val response = given()
            .`when`()
            .contentType(ContentType.JSON)
            .get(url)
            .then()
            .log().all()
            .extract()
            .response()

    val body = response.body()
    val json = body.jsonPath()
    val user: UserData = json.get<UserData>("data")


//        val user2 = given()
//            .`when`()
//            .contentType(ContentType.JSON)
//            .get(url)
//            .then()
//            .extract()
//            .response()
//            .body
//            .jsonPath()
//
//        val asd = user2.get<UserData>()


        val qwe = 1
    }

    @Test
    fun wqe() {
        val users = given()
            .`when`()
            .contentType(ContentType.JSON)
            .get("https://reqres.in/api/users?page=2")
            .then().log().all()
            .extract().body().jsonPath().getList("data", UserData::class.java)


    }

    @Test
    fun q() {
        val response = given()
            .`when`()
            .contentType(ContentType.JSON)
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200)
            .extract()
            .response()

        val state = response.body.path<ArrayList<UserData>>("data")

        //state[0].id

        val asd = 1
    }

    @Test
    fun w() {
        val response = given()
            .`when`()
            .contentType(ContentType.JSON)
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .jsonPath()

        val state = response.getList("data", UserData::class.java)
        state[0].id
        val asd = 1
    }
}