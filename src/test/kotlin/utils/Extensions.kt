package utils

import io.restassured.specification.RequestSpecification

fun RequestSpecification.When() = this.`when`()

fun String.endsWithOneOf(suffixes: List<String>): Boolean {
    suffixes.forEach {
        if (this.endsWith(it)) return true
    }

    return false
}
