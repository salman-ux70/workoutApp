package com.assignment.workoutapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.workoutapp.databinding.WorkoutItemBinding
import com.assignment.workoutapp.model.Exercise

class WorkoutAdapter(val onItemClick: (Exercise) -> Unit) :
    RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    private var exercises: List<Exercise> = emptyList()

    inner class ViewHolder(val binding: WorkoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.tvName.text = exercise.name
            binding.ivImage.setImageResource(exercise.imageRes)
            binding?.root?.setOnClickListener {
                onItemClick(exercise)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    fun setData(list: List<Exercise>) {
        this.exercises = list
        notifyDataSetChanged()
    }
}