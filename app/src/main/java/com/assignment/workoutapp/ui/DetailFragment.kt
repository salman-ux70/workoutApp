package com.assignment.workoutapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.workoutapp.R
import com.assignment.workoutapp.adapter.InstructionsAdapter
import com.assignment.workoutapp.databinding.FragmentDetailBinding
import com.assignment.workoutapp.repo.SharedPrefsManager
import com.assignment.workoutapp.viewmodel.WorkoutViewmodel
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: WorkoutViewmodel by activityViewModels()

    private var adapter: InstructionsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            viewModel.workOutItem?.let { item ->
                tvWorkoutTitle.text = item.name
                tvReps.text = item.sets
                Glide.with(requireContext()).load(item.gifUrl).into(
                    ivGift
                )
                btnDone.setOnClickListener {
                    var workoutsDone = SharedPrefsManager.getWorkoutCompleted(requireContext())
                    var totalCaloriesBurned = SharedPrefsManager.getCaloriesBurned(requireContext())
                    totalCaloriesBurned += item.caloriesBurn
                    if ((workoutsDone) < 8) {
                        workoutsDone++
                    }
                    SharedPrefsManager.saveCaloriesBurned(requireContext(), totalCaloriesBurned)
                    SharedPrefsManager.saveWorkoutCompleted(requireContext(), workoutsDone)
                    findNavController().popBackStack()
                }
                setupAdapter(requireContext(), item.instructions)
            }
        }
    }

    private fun setupAdapter(context: Context, instructions: List<String>) {
        binding.rvInstructions.layoutManager = LinearLayoutManager(context)
        adapter = InstructionsAdapter()
        binding.rvInstructions.adapter = adapter
        adapter?.setData(instructions)
    }

}