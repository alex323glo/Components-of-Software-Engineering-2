package com.alex323glo.tutorials.memoization

data class HttpRequest(val url: String,
                       val method: HttpRequestMethod,
                       val headers: Map<String, String>,
                       val body: String?)

data class HttpResponse (val status: HttpResponseStatus,
                         val body: String)

enum class HttpResponseStatus(val statusCode: Int) {
    OK              (200),
    BAD_REQUEST     (400),
    NOT_FOUND       (404)
}
enum class HttpRequestMethod {
    GET,
    POST
}