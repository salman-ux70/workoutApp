package com.assignment.workoutapp.repo

import com.assignment.workoutapp.R
import com.assignment.workoutapp.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object WorkoutRepo {
    fun getAllExercises(): Flow<List<Exercise>> = flow {
        val exercises = listOf(
            Exercise(
                exerciseId = "Hy9D21L",
                name = "45° side bend",
                imageRes = R.drawable.side_bend,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/Hy9D21L.gif",
                instructions = listOf(
                    "Step:1 Stand with your feet shoulder-width apart and your arms extended straight down by your sides.",
                    "Step:2 Keeping your back straight and your core engaged, slowly bend your torso to one side, lowering your hand towards your knee.",
                    "Step:3 Pause for a moment at the bottom, then slowly return to the starting position.",
                    "Step:4 Repeat on the other side.",
                    "Step:5 Continue alternating sides for the desired number of repetitions."
                ),
                targetMuscles = listOf("abs"),
                bodyParts = listOf("waist"),
                equipments = listOf("body weight"),
                secondaryMuscles = listOf("obliques")
            ),
            Exercise(
                exerciseId = "2gPfomN",
                name = "3/4 sit-up",
                imageRes = R.drawable.situp,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/2gPfomN.gif",
                instructions = listOf(
                    "Step:1 Lie flat on your back with your knees bent and feet flat on the ground.",
                    "Step:2 Place your hands behind your head with your elbows pointing outwards.",
                    "Step:3 Engaging your abs, slowly lift your upper body off the ground, curling forward until your torso is at a 45-degree angle.",
                    "Step:4 Pause for a moment at the top, then slowly lower your upper body back down to the starting position.",
                    "Step:5 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("abs"),
                bodyParts = listOf("waist"),
                equipments = listOf("body weight"),
                secondaryMuscles = listOf("hip flexors", "lower back")
            ),
            Exercise(
                exerciseId = "S9zHIvU",
                name = "barbell rear delt row",
                imageRes = R.drawable.rear_row,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/S9zHIvU.gif",
                instructions = listOf(
                    "Step:1 Stand with your feet shoulder-width apart and knees slightly bent.",
                    "Step:2 Hold a barbell with an overhand grip, hands slightly wider than shoulder-width apart.",
                    "Step:3 Bend forward at the hips, keeping your back straight and chest up.",
                    "Step:4 Pull the barbell towards your chest, squeezing your shoulder blades together.",
                    "Step:5 Pause for a moment at the top, then slowly lower the barbell back to the starting position.",
                    "Step:6 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("delts"),
                bodyParts = listOf("shoulders"),
                equipments = listOf("barbell"),
                secondaryMuscles = listOf("trapezius", "rhomboids", "biceps")
            ),
            Exercise(
                exerciseId = "Ln9iTbU",
                name = "barbell rear delt raise",
                imageRes = R.drawable.press_situp,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/Ln9iTbU.gif",
                instructions = listOf(
                    "Step:1 Stand with your feet shoulder-width apart and hold a barbell with an overhand grip, palms facing down.",
                    "Step:2 Bend your knees slightly and hinge forward at the hips, keeping your back straight.",
                    "Step:3 Raise the barbell out to the sides, keeping your arms straight, until they are parallel to the ground.",
                    "Step:4 Pause for a moment at the top, then slowly lower the barbell back to the starting position.",
                    "Step:5 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("delts"),
                bodyParts = listOf("shoulders"),
                equipments = listOf("barbell"),
                secondaryMuscles = listOf("traps", "rhomboids")
            ),
            Exercise(
                exerciseId = "za9Ni4z",
                name = "barbell rack pull",
                imageRes = R.drawable.barbell,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/za9Ni4z.gif",
                instructions = listOf(
                    "Step:1 Set up a barbell on a rack at knee height.",
                    "Step:2 Stand with your feet shoulder-width apart, toes pointing slightly outwards.",
                    "Step:3 Bend at the hips and knees to lower yourself down and grip the barbell with an overhand grip, hands shoulder-width apart.",
                    "Step:4 Engage your core and lift the barbell by extending your hips and knees, pulling your shoulders back and squeezing your glutes at the top.",
                    "Step:5 Lower the barbell back down to the starting position by bending at the hips and knees.",
                    "Step:6 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("glutes"),
                bodyParts = listOf("upper legs"),
                equipments = listOf("barbell"),
                secondaryMuscles = listOf("hamstrings", "lower back")
            ),
            Exercise(
                exerciseId = "znLogoF",
                name = "barbell pullover to press",
                imageRes = R.drawable.barbell_pullover,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/znLogoF.gif",
                instructions = listOf(
                    "Step:1 Lie flat on a bench with your head at one end and your feet on the ground.",
                    "Step:2 Hold the barbell with a pronated grip (palms facing away from you) and extend your arms straight above your chest.",
                    "Step:3 Keeping your arms straight, lower the barbell behind your head in an arc-like motion until you feel a stretch in your lats.",
                    "Step:4 Pause for a moment, then reverse the motion and press the barbell back to the starting position above your chest.",
                    "Step:5 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("lats"),
                bodyParts = listOf("back"),
                equipments = listOf("barbell"),
                secondaryMuscles = listOf("triceps", "chest", "shoulders")
            ),
            Exercise(
                exerciseId = "i6LWjok",
                name = "barbell pullover",
                imageRes = R.drawable.barbell,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/i6LWjok.gif",
                instructions = listOf(
                    "Step:1 Lie flat on a bench with your head at one end and your feet on the floor.",
                    "Step:2 Hold a barbell with a shoulder-width grip and extend your arms straight above your chest.",
                    "Step:3 Keeping your arms straight, lower the barbell behind your head in a controlled manner until you feel a stretch in your lats.",
                    "Step:4 Pause for a moment, then raise the barbell back to the starting position.",
                    "Step:5 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("lats"),
                bodyParts = listOf("back"),
                equipments = listOf("barbell"),
                secondaryMuscles = listOf("chest", "triceps")
            ),

            Exercise(
                exerciseId = "wnEscH8",
                name = "barbell press sit-up",
                imageRes = R.drawable.press_situp,
                gifUrl = "https://cdn-exercisedb.vercel.app/api/v1/images/wnEscH8.gif",
                instructions = listOf(
                    "Step:1 Lie flat on your back on a mat with your knees bent and feet flat on the ground.",
                    "Step:2 Hold the barbell with an overhand grip, resting it on your chest.",
                    "Step:3 Engaging your abs, slowly lift your upper body off the ground, curling forward until your torso is at a 45-degree angle.",
                    "Step:4 Pause for a moment at the top, then slowly lower your upper body back down to the starting position.",
                    "Step:5 Repeat for the desired number of repetitions."
                ),
                targetMuscles = listOf("abs"),
                bodyParts = listOf("waist"),
                equipments = listOf("body weight"),
                secondaryMuscles = listOf("hip flexors", "lower back")
            )
        )
        emit(exercises)
    }.flowOn(Dispatchers.IO)
}