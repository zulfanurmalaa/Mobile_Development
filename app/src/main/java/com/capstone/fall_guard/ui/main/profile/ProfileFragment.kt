package com.capstone.fall_guard.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.capstone.fall_guard.R
import com.capstone.fall_guard.databinding.FragmentProfileBinding
import com.capstone.fall_guard.databinding.FragmentStarterBinding
import com.capstone.fall_guard.ui.main.metrics.MetricsFragmentDirections

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        overrideBackPress()

        setListeners()

        return root
    }

    private fun setListeners() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            btnMetrics.setOnClickListener {
                val action = ProfileFragmentDirections.actionNavigationProfileToNavigationMetrics()
                findNavController().navigate(action)
            }

            btnHome.setOnClickListener {
                val action = ProfileFragmentDirections.actionNavigationProfileToNavigationMetrics()
                findNavController().navigate(action)
            }
        }
    }

    private fun overrideBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.navigation_starter)
                }
            })
    }
}