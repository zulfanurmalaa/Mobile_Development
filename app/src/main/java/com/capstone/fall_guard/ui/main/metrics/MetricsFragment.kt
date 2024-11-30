package com.capstone.fall_guard.ui.main.metrics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.fall_guard.R
import com.capstone.fall_guard.databinding.FragmentMetricsBinding
import com.capstone.fall_guard.databinding.FragmentStarterBinding
import com.capstone.fall_guard.ui.adapters.HistoryAdapter
import com.capstone.fall_guard.ui.main.home.HomeFragmentDirections
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class MetricsFragment : Fragment() {
    private var _binding: FragmentMetricsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMetricsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        overrideBackPress()

        val dummyTimestamp1: Long = System.currentTimeMillis() // Current timestamp
        val dummyFallHistory1: MutableList<Long> = mutableListOf(
            dummyTimestamp1 - 60 * 60 * 1000, // 1 hour ago
            dummyTimestamp1 - 2 * 60 * 60 * 1000 // 2 hours ago
        )

        val dummyTimestamp2: Long = System.currentTimeMillis() - 24 * 60 * 60 * 1000 // 1 day ago
        val dummyFallHistory2: MutableList<Long> = mutableListOf(
            dummyTimestamp2 - 30 * 60 * 1000, // 30 minutes before timestamp2
            dummyTimestamp2 - 90 * 60 * 1000 // 1.5 hours before timestamp2
        )

        injectDaySectionHistory(dummyTimestamp1, dummyFallHistory1)
        injectDaySectionHistory(dummyTimestamp2, dummyFallHistory2)

        setListeners()

        return root
    }

    private fun injectDaySectionHistory(timestamp: Long, fallHistory: MutableList<Long>) {
        val dateFormatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("h:mm a", Locale.ENGLISH)

        val sectionText = dateFormatter.format(timestamp)
        val mappedFallListString =
            fallHistory.map { timeFormatter.format(it) ?: "" }.toMutableList()

        binding.apply {
            val tvSection = TextView(requireContext()).apply {
                text = sectionText
                textSize = 18f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                setPadding(16, 16, 16, 8)
            }

            val rvHistory = RecyclerView(requireContext()).apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = HistoryAdapter().apply {
                    submitList(mappedFallListString)
                }
                setPadding(16, 0, 16, 16)
            }

            historyLayout.addView(tvSection)
            historyLayout.addView(rvHistory)
        }
    }

    private fun setListeners() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            btnHome.setOnClickListener {
                val action = MetricsFragmentDirections.actionNavigationMetricsToNavigationHome()
                findNavController().navigate(action)
            }

            btnProfile.setOnClickListener {
                val action = MetricsFragmentDirections.actionNavigationMetricsToNavigationProfile()
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