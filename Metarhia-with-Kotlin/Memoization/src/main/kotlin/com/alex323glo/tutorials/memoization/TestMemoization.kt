package com.alex323glo.tutorials.memoization

import java.util.*
import kotlin.math.absoluteValue

fun main(args: Array<String>) {

    val headerValues = arrayOf("alex123", "max456")
    val requestMethodsValues = arrayOf(HttpRequestMethod.GET, HttpRequestMethod.POST)
    val urlValues = arrayOf("/test", "/resource")
    val random = Random()
    val client = HttpClient(MyPool)

    for (i in 1..100) {
        client.sendRequest(
                HttpRequest(
                        urlValues[random.nextInt().absoluteValue % 2],
                        requestMethodsValues[random.nextInt().absoluteValue % 2],
                        mapOf("username" to headerValues[random.nextInt().absoluteValue % 2]),
                        "some_body"
                ),
                MyServer
        )
    }

}
