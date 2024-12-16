package com.example.handyhive.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.handyhive.Ambulance
import com.example.handyhive.Cartpenter
import com.example.handyhive.Electric
import com.example.handyhive.PlumberActivtiy
import com.example.handyhive.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // Binding for accessing views defined in the layout
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! // Ensures binding is only valid when the view exists

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set click listeners for the cards
        binding.card1.setOnClickListener {
            openActivity(PlumberActivtiy::class.java)
        }
        binding.card2.setOnClickListener {
            openActivity(Electric::class.java)
        }
        binding.card3.setOnClickListener {
            openActivity(Cartpenter::class.java)
        }
        binding.card5.setOnClickListener {
            openActivity(Ambulance::class.java)
        }

        // Return the root view
        return root
    }

    // Function to open another activity
    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(requireContext(), activityClass)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the binding to avoid memory leaks
        _binding = null
    }
}


