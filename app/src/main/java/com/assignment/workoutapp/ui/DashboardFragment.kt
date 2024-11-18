package com.assignment.workoutapp.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.assignment.workoutapp.callback.stepsCallback
import com.assignment.workoutapp.databinding.FragmentDashboardBinding
import com.assignment.workoutapp.extensions.showSettings
import com.assignment.workoutapp.repo.SharedPrefsManager
import com.assignment.workoutapp.service.StepDetectorService
import com.assignment.workoutapp.util.GeneralHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DashboardFragment : Fragment(), stepsCallback {
    private lateinit var binding: FragmentDashboardBinding

    private var mActivity: FragmentActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity?.let { activity ->
            binding.setWorkoutData()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (GeneralHelper.hasSensorPermissions(activity)) {
                    startStepCounter()
                } else {
                    sensorPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                }
            } else {
                startStepCounter()
            }


        }
    }

    override fun subscribeSteps(steps: Int) {
        binding.tvSteps.text = "$steps / 6000 \nsteps"
        val progress = steps / 6000
        binding.circularProgressBar.progress = progress.toFloat()
    }


    private val sensorPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission: Boolean ->
            if (permission) {
                mActivity?.let { activity ->
                    binding?.apply {
                        startStepCounter()
                    }
                }
            } else {
                activity?.runOnUiThread {
                    context?.let { context ->
                        MaterialAlertDialogBuilder(context).setTitle("Permission Required")
                            .setMessage("These permissions are required for this purpose")
                            .setCancelable(false).setNegativeButton("Deny") { dialog, _ ->

                                dialog.dismiss()
                            }.setPositiveButton("Grant") { _, _ ->
                                context.showSettings()
                            }.show()
                    }
                }
            }
        }

    private fun startStepCounter() {
        val intent = Intent(requireActivity(), StepDetectorService::class.java)
        requireActivity().startService(intent)

        StepDetectorService.subscribe.register(this@DashboardFragment)


    }

    fun FragmentDashboardBinding.setWorkoutData() {
        val workOutDone = SharedPrefsManager.getWorkoutCompleted(requireContext())
        val caloriesBurned = SharedPrefsManager.getCaloriesBurned(requireContext())
        tcCaloriesStatus.text = "$caloriesBurned burned"
        tvWorkoutCompleted.text = "$workOutDone/8 completed"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as FragmentActivity
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

}