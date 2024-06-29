package utils

import io.restassured.RestAssured.given
import io.restassured.http.ContentType

inline fun <reified T> getEntity(
    url: String,
    contentType: ContentType = ContentType.JSON
): T {
    return given()
        .When()
        .log().all()
        .contentType(contentType)
        .get(url)
        .then()
        .log().all()
        .extract()
        .body()
        .`as`(T::class.java)
}
