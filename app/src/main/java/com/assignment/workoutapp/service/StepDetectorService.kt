package com.assignment.workoutapp.service

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.assignment.workoutapp.callback.stepsCallback
import com.assignment.workoutapp.repo.SharedPrefsManager
import com.assignment.workoutapp.util.GeneralHelper
import kotlin.math.roundToInt


class StepDetectorService : Service(), SensorEventListener {

    companion object {
        lateinit var callback: stepsCallback
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val countSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (countSensor != null) {
            Toast.makeText(this, "Step Detecting Start", Toast.LENGTH_SHORT).show()
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL)
            GeneralHelper.updateNotification(this, this, SharedPrefsManager.getFinalSteps(this))

            callback.subscribeSteps(SharedPrefsManager.getStepsCompleted(this))

        } else {
            Toast.makeText(this, "Sensor Not Detected", Toast.LENGTH_SHORT).show()

        }
        return START_STICKY

    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (SharedPrefsManager.getDate(this) != GeneralHelper.getToadyDate()) {
            SharedPrefsManager.saveStepsTaken(this, p0!!.values[0].roundToInt())
            SharedPrefsManager.saveDate(this, GeneralHelper.getToadyDate())
        } else {
            val storeSteps = SharedPrefsManager.getStepsCompleted(this)
            val sensorSteps = p0!!.values[0].roundToInt()
            val finalSteps = sensorSteps - storeSteps
            if (finalSteps > 0) {
                SharedPrefsManager.saveFinalSteps(this, finalSteps)
                GeneralHelper.updateNotification(this, this, finalSteps)
                callback?.subscribeSteps(finalSteps)
            }

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d("SERVICE", p0.toString())

    }

    object subscribe {
        fun register(callbackReceiver: stepsCallback) {
            callback = callbackReceiver
        }
    }

}