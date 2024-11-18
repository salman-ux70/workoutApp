package com.assignment.workoutapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.workoutapp.databinding.InstructionsItemBinding
import com.assignment.workoutapp.databinding.WorkoutItemBinding
import com.assignment.workoutapp.model.Exercise

class InstructionsAdapter() :
    RecyclerView.Adapter<InstructionsAdapter.ViewHolder>() {
    private var instructions: List<String> = emptyList()

    inner class ViewHolder(val binding: InstructionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: String) {
            binding.tvInstruction.text = exercise

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            InstructionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = instructions[position]
        holder.bind(exercise)
    }

    fun setData(list: List<String>) {
        this.instructions = list
        notifyDataSetChanged()
    }
}