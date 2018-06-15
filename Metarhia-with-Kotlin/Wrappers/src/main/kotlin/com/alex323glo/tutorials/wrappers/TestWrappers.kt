package com.alex323glo.tutorials.wrappers

fun main(args: Array<String>) {
    testFunctionWrapper()
    testClassWrapper()
    testObjectWrappers()
}

/**
 * Test function wrapper.
 */
fun testFunctionWrapper() {
    println("\nTesting function wrappers:")

    val a = 4
    val b = 5

    val simpleSun = a.sum(b, false)
    val loggedSum = a.sum(b, true)  // prints logs!

    println("simpleSum and loggedSum are equal? - ${simpleSun == loggedSum}")
}

/**
 * Test class wrappers.
 */
fun testClassWrapper() {
    println("\nTesting class wrappers:")

    val businessmen1 = Businessmen(Phone("+38-068-388-33-88"))
    val businessmen2 = Businessmen(BlackBerry("+38-077-777-77-77"))

    println("businessmen1.makeAnImportantCall(): \n${businessmen1.makeAnImportantCall("*111#")}")
    println("businessmen1.makeASimpleCall(): \n${businessmen1.makeASimpleCall(businessmen2.phone.phoneNumber)}")
    println("businessmen1.makeAnImportantCall(): \n${businessmen1.makeAnImportantCall("911")}")

    println("businessmen2.makeAnImportantCall(): \n${businessmen2.makeAnImportantCall("*100#")}")
    println("businessmen2.makeASimpleCall(): \n${businessmen2.makeASimpleCall(businessmen1.phone.phoneNumber)}")
    println("businessmen2.makeAnImportantCall(): \n${businessmen2.makeAnImportantCall("911")}")
}

/**
 * Test object wrappers.
 */
fun testObjectWrappers() {
    println("\nTesting object wrappers:")

    val simpleServer: HttpServer = SimpleHttpServer
    val securedServer: HttpServer = SecuredHttpServer
    val myService = MyService

    myService.init(simpleServer)
    myService.start()
    myService.interact("GET", "/resource", null, null)
    myService.interact("GET", "/resource", null, arrayOf("secret: SIMPLE_SECRET"))
    myService.interact("GET", "/resource", null, arrayOf("secret: TOP_SECRET"))
    myService.interact("POST", "/resource", "rec1", null)
    myService.interact("POST", "/resource", "rec2", arrayOf("secret: SIMPLE_SECRET"))
    myService.interact("POST", "/resource", "rec3", arrayOf("secret: TOP_SECRET"))
    myService.stop()

    myService.init(securedServer)
    myService.start()
    myService.interact("GET", "/resource", null, null)
    myService.interact("GET", "/resource", null, arrayOf("secret: SIMPLE_SECRET"))
    myService.interact("GET", "/resource", null, arrayOf("secret: TOP_SECRET"))
    myService.interact("POST", "/resource", "rec1", null)
    myService.interact("POST", "/resource", "rec2", arrayOf("secret: SIMPLE_SECRET"))
    myService.interact("POST", "/resource", "rec3", arrayOf("secret: TOP_SECRET"))
    myService.stop()

}