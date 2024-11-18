package com.assignment.workoutapp.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.assignment.workoutapp.R
import com.assignment.workoutapp.databinding.ActivityMainBinding
import com.assignment.workoutapp.extensions.showSettings
import com.assignment.workoutapp.util.GeneralHelper
import com.assignment.workoutapp.viewmodel.WorkoutViewmodel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (GeneralHelper.hasSensorPermissions(this)) {

            } else {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navhosFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        // Set up BottomNavigationView with NavController
        binding.bottomView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.dashboardFragment || destination.id == R.id.workoutFragment) {

                binding.bottomView.visibility = View.VISIBLE
            } else {

                binding.bottomView.visibility = View.GONE
            }
        }
    }

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission: Boolean ->
            if (permission) {
            } else {
                runOnUiThread {
                    MaterialAlertDialogBuilder(this).setTitle("Permission Required")
                        .setMessage("Notification permissions are required for this purpose")
                        .setCancelable(false).setNegativeButton("Deny") { dialog, _ ->

                            dialog.dismiss()
                        }.setPositiveButton("Grant") { _, _ ->
                            this.showSettings()
                        }.show()

                }
            }
        }

}