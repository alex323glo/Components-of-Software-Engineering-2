package com.alex323glo.tutorials.wrappers

import java.time.LocalTime

/**
 *
 */
open class Phone (var phoneNumber: String) {

    val calls: MutableMap<LocalTime, PhoneCall> by lazyOf(HashMap())

    open fun call(receiverPhoneNumber: String, durationSeconds: Int): String {
        val currentTime = LocalTime.now()
        val callPrint = """
    Calling to $receiverPhoneNumber from $phoneNumber on $currentTime ...
    Talking for $durationSeconds seconds...
    Call ended!
            """
        calls[currentTime] = PhoneCall(phoneNumber, receiverPhoneNumber, durationSeconds)
        return callPrint
    }

    data class PhoneCall (val providerNumber: String, val receiverNumber: String, val durationSeconds: Int)
}

class BlackBerry (phoneNumber: String): Phone(phoneNumber) {
    override fun call(receiverPhoneNumber: String, durationSeconds: Int): String {
        val preCallPrint =
                """
    Ringtone: $$$ I'm cool, because I'm using BlackBerry!!! 8) $$$ ...
    Ringtone: $$$ I'm cool, because I'm using BlackBerry!!! 8) $$$ ...
    Ringtone: $$$ I'm cool, because I'm using BlackBerry!!! 8) $$$ ... (receiver responded)
                    """
        val callPrint = super.call(receiverPhoneNumber, durationSeconds)
        return preCallPrint + callPrint
    }
}

class Businessmen (var phone: Phone) {

    private val IMPORTANT_CALL_DURATION = 120
    private val SIMPLE_CALL_DURATION = 60
    private val EMERGENCY_CALL_DURATION = 30

    fun makeAnImportantCall(phoneNumber: String): String = phone.call(phoneNumber, IMPORTANT_CALL_DURATION)

    fun makeASimpleCall(phoneNumber: String): String = phone.call(phoneNumber, SIMPLE_CALL_DURATION)

    fun makeAnEmergencyCall(phoneNumber: String): String = phone.call(phoneNumber, EMERGENCY_CALL_DURATION)

}