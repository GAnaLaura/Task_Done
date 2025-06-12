package com.example.taskdone.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.taskdone.databinding.ActivityOnboardingBinding
import com.example.taskdone.utils.FragmentCommunicator

class OnboardingActivity : AppCompatActivity(), FragmentCommunicator     {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showLoader(value: Boolean) {
        binding.loaderContainerView.visibility = if (value) View.VISIBLE else View.GONE
    }

}