package utils

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

inline fun <reified responseType> getEntity(
    url: String,
    contentType: ContentType = ContentType.JSON
): Entity<responseType> {
    val request = given()
        .contentType(contentType)
        .When()
        .log().all()

    val response = request.get(url)
    val entity = getEntity<responseType>(response)
    val code = response.statusCode

    return Entity(entity, code)
}

inline fun <reified responseType, reified requestBodyType> postWithResponseEntity(
    url: String,
    requestBody: requestBodyType,
    contentType: ContentType = ContentType.JSON
): Entity<responseType> {
    val request = getRequest<requestBodyType>(requestBody, contentType)
    val response = request.post(url)

    val entity = getEntity<responseType>(response)
    val code = response.statusCode

    return Entity(entity, code)
}

inline fun <reified responseType, reified requestBodyType> putWithResponseEntity(
    url: String,
    requestBody: requestBodyType,
    contentType: ContentType = ContentType.JSON
): Entity<responseType> {
    val request = getRequest<requestBodyType>(requestBody, contentType)
    val response = request.put(url)

    val entity = getEntity<responseType>(response)
    val code = response.statusCode

    return Entity(entity, code)
}

inline fun <reified responseType, reified requestBodyType> patchWithResponseEntity(
    url: String,
    requestBody: requestBodyType,
    contentType: ContentType = ContentType.JSON
): Entity<responseType> {
    val request = getRequest<requestBodyType>(requestBody, contentType)
    val response = request.patch(url)

    val entity = getEntity<responseType>(response)
    val code = response.statusCode

    return Entity(entity, code)
}

inline fun <reified requestBodyType> deleteEntity(
    url: String,
    requestBody: requestBodyType,
    contentType: ContentType = ContentType.JSON
): Int {
    val request = getRequest<requestBodyType>(requestBody, contentType)
    val response = request.delete(url)

    return response.statusCode
}

inline fun <reified T> getRequest(
    requestBody: T,
    contentType: ContentType
): RequestSpecification {
    return given()
        .When()
        .log().all()
        .contentType(contentType)
        .body(requestBody)
}

inline fun <reified T> getEntity(response: Response): T {
    return response
        .then()
        .log().all()
        .extract()
        .body()
        .`as`(T::class.java)
}

data class Entity<T>(
    val entity: T,
    val responseCode: Int
)

class ResponseCode {
    companion object {
        const val SUCCESS = 200
        const val CREATED = 201
        const val NO_CONTENT = 204

        const val BAD_REQUEST = 400
        const val NOT_FOUND = 404
    }
}
