package utils

import io.restassured.RestAssured.given
import io.restassured.http.ContentType

inline fun <reified responseType> getEntity(
    url: String,
    contentType: ContentType = ContentType.JSON
): Entity<responseType> {
    val response = given()
        .When()
        .log().all()
        .contentType(contentType)
        .get(url)

    val entity = response
        .then()
        .log().all()
        .extract()
        .body()
        .`as`(responseType::class.java)

    val code = response.statusCode

    return Entity(entity, code)
}

inline fun <reified responseType, reified requestBodyType> postWithResponseEntity(
    url: String,
    requestBody: requestBodyType,
    contentType: ContentType = ContentType.JSON
): Entity<responseType> {
    val request = given()
        .contentType(contentType)
        .body(requestBody)

    val response = request
        .When()
        .log().all()
        .post(url)

    val entity = response
        .then()
        .log().all()
        .extract()
        .body()
        .`as`(responseType::class.java)

    val code = response.statusCode

    return Entity(entity, code)
}

data class Entity<T>(
    val entity: T,
    val responseCode: Int
)

class ResponseCode {
    companion object {
        const val SUCCESS = 200

        const val BAD_REQUEST = 400
        const val NOT_FOUND = 404
    }
}
