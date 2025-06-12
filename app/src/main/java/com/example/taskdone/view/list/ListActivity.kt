package com.example.taskdone.view.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taskdone.databinding.ActivityListBinding
import com.example.taskdone.utils.FragmentCommunicator

class ListActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showLoader(value: Boolean) {
        binding.list.visibility = if (value) View.VISIBLE else View.GONE
    }
}