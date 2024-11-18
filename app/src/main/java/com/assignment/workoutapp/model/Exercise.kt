package com.assignment.workoutapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val exerciseId: String,
    val name: String,
    val imageRes: Int,
    val gifUrl: String,
    val instructions: List<String>,
    val targetMuscles: List<String>,
    val bodyParts: List<String>,
    val equipments: List<String>,
    val secondaryMuscles: List<String>,
    var sets: String = "3 sets 12 reps",
    var caloriesBurn : Int = 15
)
