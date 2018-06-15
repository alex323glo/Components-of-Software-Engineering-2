package com.alex323glo.tutorials.memoization

/**
 * HTTP Request-Response Pool Interface, which stores pairs (HttpRequest, HttpResponse).
 */
interface HttpResponsePool {
    fun getOrNull(request: HttpRequest): HttpResponse?
    fun put(request: HttpRequest, response: HttpResponse)
}