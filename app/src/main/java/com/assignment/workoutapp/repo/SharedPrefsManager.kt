package com.assignment.workoutapp.repo

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsManager {
    private const val PREFS_NAME = "AppPreferences"
    private const val WOKROUT_COMPLETED = "workout_completed"
    private const val CALORIES_BURNED = "calories_burned"
    private const val STEPS_COUNTER = "steps_counter"
    private const val FINAL_STEPS = "final_steps"

    private const val TODAY_DATE = "today_date"

    fun getSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveWorkoutCompleted(context: Context, value: Int) {
        getSharedPrefs(context).edit().putInt(WOKROUT_COMPLETED, value).apply()
    }

    fun saveFinalSteps(context: Context, value: Int) {
        getSharedPrefs(context).edit().putInt(FINAL_STEPS, value).apply()
    }

    fun saveDate(context: Context, value: String) {
        getSharedPrefs(context).edit().putString(TODAY_DATE, value).apply()
    }

    fun saveStepsTaken(context: Context, value: Int) {
        getSharedPrefs(context).edit().putInt(STEPS_COUNTER, value).apply()
    }

    fun getFinalSteps(context: Context): Int {
        return getSharedPrefs(context).getInt(FINAL_STEPS, 0)
    }

    fun saveCaloriesBurned(context: Context, value: Int) {
        getSharedPrefs(context).edit().putInt(CALORIES_BURNED, value).apply()
    }

    fun getDate(context: Context): String {
        return getSharedPrefs(context).getString(TODAY_DATE, "") ?: ""
    }

    fun getStepsCompleted(context: Context): Int {
        return getSharedPrefs(context).getInt(STEPS_COUNTER, 0)
    }

    fun getWorkoutCompleted(context: Context): Int {
        return getSharedPrefs(context).getInt(WOKROUT_COMPLETED, 0)
    }

    fun getCaloriesBurned(context: Context): Int {
        return getSharedPrefs(context).getInt(CALORIES_BURNED, 0)
    }
}