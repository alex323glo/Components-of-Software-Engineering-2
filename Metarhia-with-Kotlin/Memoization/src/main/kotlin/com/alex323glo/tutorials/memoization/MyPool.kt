package com.alex323glo.tutorials.memoization

/**
 * Response Pool implementation, which is based on storing pairs (HttpRequest, HttpRespons),
 * as (key, value) pairs, to Hash Map.
 */
object MyPool: HttpResponsePool {

    private val responseMap: MutableMap<HttpRequest, HttpResponse> = HashMap()

    override fun getOrNull(request: HttpRequest): HttpResponse? {
        val response = responseMap.get(request)
        if (response != null) {
            println("*** MyPool: Successfully found response ($response), which matches request ($request).")
        }
        return response
    }

    override fun put(request: HttpRequest, response: HttpResponse) {
        println("*** MyPool: Put new response ($response) to pool with key request ($request)")
        responseMap.put(request, response)
    }

}