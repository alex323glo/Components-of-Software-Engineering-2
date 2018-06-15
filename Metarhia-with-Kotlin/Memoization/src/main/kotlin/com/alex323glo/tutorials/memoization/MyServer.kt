package com.alex323glo.tutorials.memoization

object MyServer: HttpServer {
    override fun response(request: HttpRequest): HttpResponse {
        println("*** MyServer: Serving request ($request)...")

        val numberOfOperations: Int = (Math.random() * 10).toInt()

        for (i in 1..numberOfOperations) {
            Thread.sleep(250)
            println("*** MyServer: performing something heavy ...$i")
        }
        val response = HttpResponse(HttpResponseStatus.OK, "\"$request\"")

        println("*** MyServer: Successfully responded ($response) to request ($request)")
        return response
    }
}