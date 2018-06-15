package com.alex323glo.tutorials.wrappers

/**
 * HTTP Server interface.
 */
interface HttpServer {
    fun receiveRequest(method: String, url: String, body: String?, cookies: Array<String>?): String

    var port: Int
    var host: String
}

/**
 * Simple implementation of HTTP Server Interface.
 */
object SimpleHttpServer: HttpServer {
    override var port = 8080
    override var host = "localhost"

    override fun receiveRequest(method: String, url: String, body: String?, cookies: Array<String>?): String =
            when(method.toUpperCase()) {
                "GET" -> "200: Successfully served GET request with URL \"$url\"!"
                "POST" -> "200: Successfully served POST request with URL \"$url\" and body \"$body\"!"
                else -> "404: Not Found!"
            }
}

/**
 * Implementation of HTTP Server Interface and Decorator of SimpleHttpServer.
 *
 * Wraps SimpleHttpServer and acts as HTTP Listener -
 * intercepts HTTP Request before SimpleHttpServer receives them.
 */
object SecuredHttpServer: HttpServer {

    private val server = SimpleHttpServer

    override var port: Int
        get() = server.port
        set(value) {
            server.port = value
        }

    override var host: String
        get() = server.host
        set(value) {
            server.host = value
        }

    override fun receiveRequest(method: String, url: String, body: String?, cookies: Array<String>?): String =
            when {
                cookies != null && cookies.contains("secret: TOP_SECRET") ->
                    server.receiveRequest(method, url, body, cookies)
                else -> "401: Unauthorized!"
            }
}

/**
 * Container of HTTP Server - such a wrapper over some HttpServer implementation.
 */
object MyService {

    private var requestCounter = 0

    private lateinit var httpServer: HttpServer

    private var isWorking: Boolean = false

    fun init(httpServer: HttpServer): Boolean = if (!isWorking) {
        this.httpServer = httpServer
        println("*** MyService: Initialized HTTP Server with ${httpServer::class}.")
        true
    } else {
        println("*** MyService: ERROR! Can't re-initialize HTTP Server, which is currently running!")
        false
    }

    fun start(): Boolean = if (::httpServer.isInitialized) {
        isWorking = true
        println("*** MyService: started HTTP Server on \"${httpServer.host}:${httpServer.port}\".")
        true
    } else {
        println("*** MyService: ERROR! Can't start not initialized server!")
        false
    }

    fun stop(): Boolean = when {
        isWorking -> {
            isWorking = false
            println("*** MyService: Stopped HTTP Server.")
            true
        }
        ::httpServer.isInitialized -> {
            println("*** MyService: ERROR! Can't stop HTTP Server, when it is not working!")
            false
        }
        else -> {
            println("*** MyService: ERROR! Can't stop non-initialized HTTP Server!")
            false
        }
    }

    fun interact(method: String, url: String, body: String?, cookies: Array<String>?) =
            if (isWorking) {
                requestCounter++

                println("*** MyService: Performing HTTP Request " +
                        "(hash=\"${requestCounter}\", method=\"$method\", url=\"$url\")...")

                val response = httpServer.receiveRequest(method, url, body, cookies)

                println("*** MyService: Response on request " +
                        "(hash=\"${requestCounter}\", method=\"$method\", url=\"$url\"): \n\t$response")
            } else {
                println("*** MyService: ERROR! Can't interact with not working HTTP Server!")
            }

}
