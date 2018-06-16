package com.alex323glo.tutorials.callbacks

import java.time.LocalTime

/**
 * Demonstration of callback via "Clock+Alarm" model
 * (where callback is used to serve logic of async alarm "ringer").
 */

fun main(args: Array<String>) {

    val time = LocalTime.now()!!
    val alarmRingerCallBack = {
        for (i in 1..10) {
            println("*** Clock: !!! AAAAAA - LAAAAARRRRR - MAAAAAAA !!! ($i)")
            Thread.sleep(500)
        }
    }

    // Initializing Clock with 2 alarms and "ringer" function (callback):
    val clock = Clock()
            .addAlarm(Triple(time.hour, time.minute, time.second + 10))
            .addAlarm(Triple(time.hour, time.minute, time.second + 20))
            .setAlarmRinger(alarmRingerCallBack)

    // Starting clock (clock will "tick" for 30 seconds):
    clock.start()
    Thread.sleep(30 * 1_000)
    clock.stop()
}
