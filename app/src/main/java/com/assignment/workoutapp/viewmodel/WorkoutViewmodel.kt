package com.assignment.workoutapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.workoutapp.R
import com.assignment.workoutapp.model.Exercise
import com.assignment.workoutapp.repo.WorkoutRepo.getAllExercises
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.InputStream

class WorkoutViewmodel : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> get() = _exercises

    var workOutItem : Exercise ?= null


     fun loadExercises() {
        viewModelScope.launch {
            getAllExercises().collect { exerciseList ->
                _exercises.value = exerciseList
            }
        }
    }


}