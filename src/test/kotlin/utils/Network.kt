package utils

import io.restassured.RestAssured.given
import io.restassured.http.ContentType

inline fun <reified T> getEntity(
    url: String,
    contentType: ContentType = ContentType.JSON
): Entity<T> {
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
        .`as`(T::class.java)

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
        const val NOT_FOUND = 404
    }
}
