package com.alex323glo.tutorials.callbacks

import java.time.LocalTime
import kotlin.concurrent.thread

/**
 * Simple Clock model.
 *
 * After "start" (invoke start()) it will "tick" (display current time as "hour : minute : second" every second)
 * until "stop" (invoke stop()).
 *
 * To add/remove alarms (time points - a Triple of hour, minute and second, when clock must "ring")
 * addAlarm()/removeAlarm() must be invoked. Can be done ONLY when alarm is stopped!
 *
 * Also, list of alarms could be passed to main constructor.
 *
 * To add "ring" function (some logic of alarm) callback of type "() -> Unit" must be passed to
 * setAlarmRinger() function.
 */
class Clock(private val alarms: MutableList<Triple<Int, Int, Int>> = ArrayList()) {

    var clockThreadBlock: () -> Unit

    lateinit var alarmCallback: () -> Unit
    lateinit var clockThread: Thread

    init {
        clockThreadBlock = {
            println("*** Clock: starting clock!")
            var currentTime: LocalTime
            while (true) {
                Thread.sleep(1000)

                currentTime = LocalTime.now()
                println("${currentTime.hour} : ${currentTime.minute} : ${currentTime.second}")

                if (alarms.contains(Triple(currentTime.hour, currentTime.minute, currentTime.second)) &&
                        ::alarmCallback.isInitialized) {

                    thread { alarmCallback() }

                }
            }
        }
    }

    fun addAlarm(alarm: Triple<Int, Int, Int>): Clock {
        if (!::clockThread.isInitialized || !clockThread.isAlive) {
            alarms += alarm
        } else {
            println("*** Clock: ERROR! Clack is running! Stop the clock to update alarm!")
        }
        return this
    }

    fun replaceAlarm(alarm: Triple<Int, Int, Int>): Clock {
        if (!::clockThread.isInitialized || !clockThread.isAlive) {
            alarms.remove(alarm); Unit
        } else {
            println("*** Clock: ERROR! Clack is running! Stop the clock to update alarm!")
        }
        return this
    }

    fun setAlarmRinger(alarmCallback: () -> Unit): Clock {
        if (!::clockThread.isInitialized || !clockThread.isAlive) {
            this.alarmCallback = alarmCallback
        } else {
            println("*** Clock: ERROR! Clack is running! Stop the clock to update alarm!")
        }
        return this
    }

    fun start(): Clock {
        clockThread = thread { clockThreadBlock.invoke() }
        println("*** Clock: ${if (clockThread.isAlive) "started." else "can't start!"}")
        return this
    }

    fun stop(): Clock {
        if (::clockThread.isInitialized) {
            clockThread.interrupt()
        } else {
            println("*** Clock: ERROR! Can't stop clock - clock wasn't started!")
        }
        return this
    }

}