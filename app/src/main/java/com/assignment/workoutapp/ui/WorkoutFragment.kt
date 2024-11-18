package com.assignment.workoutapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.workoutapp.R
import com.assignment.workoutapp.adapter.WorkoutAdapter
import com.assignment.workoutapp.databinding.FragmentWorkoutBinding
import com.assignment.workoutapp.viewmodel.WorkoutViewmodel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class WorkoutFragment : Fragment() {
    private val viewModel: WorkoutViewmodel by activityViewModels()
    private lateinit var binding: FragmentWorkoutBinding
    private var adapter: WorkoutAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            viewModel.loadExercises()
            setAdapter(requireContext())
        }
    }

    private fun setAdapter(context: Context) {
        lifecycleScope.launch {
            viewModel.exercises.collectLatest { list ->
                Log.d("list_items", "$list")
                binding.rvWorkout.layoutManager = LinearLayoutManager(context)
                adapter = WorkoutAdapter() { item ->
                    viewModel.workOutItem = item

                    findNavController().navigate(WorkoutFragmentDirections.actionWorkoutFragmentToDetailFragment())
                }
                binding.rvWorkout.adapter = adapter
                adapter?.setData(list)
            }
        }

    }

}