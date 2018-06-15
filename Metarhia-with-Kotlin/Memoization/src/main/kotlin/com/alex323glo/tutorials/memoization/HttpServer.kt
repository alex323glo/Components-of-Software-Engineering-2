package com.alex323glo.tutorials.memoization

/**
 * Simple HTTP Server, which responses to given requests.
 *
 * BUT be careful!  Response creation is a VERY-LONG-TIME operation ;)
 */
interface HttpServer {

    fun response(request: HttpRequest): HttpResponse

}