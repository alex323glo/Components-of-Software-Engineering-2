package com.alex323glo.tutorials.memoization

/**
 * Simple HTTP Client, which could send requests to direct HTTP Server.
 */
class HttpClient(var responsePool: HttpResponsePool) {

    /**
     * Checks if such request was send before (using responsePool) and,
     * if such request was sent before, sends existent response, instead of
     * creating new one (imagine, that server response is VERY-VERY HEAVY operation)!
     */
    fun sendRequest(request: HttpRequest, server: HttpServer): HttpResponse {
        var response = responsePool.getOrNull(request)

        return if (response == null) {
            response = server.response(request)
            responsePool.put(request, response)
            response
        } else {
            response
        }
    }

}