package com.example.material_components_demo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.material_components_demo.R
import com.example.material_components_demo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetManager: BottomSheetManager
    private lateinit var actionBarManager: ActionBarManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bottomSheetManager = BottomSheetManager(this)
        actionBarManager = ActionBarManager(this)
        actionBarManager.setupActionBar(binding.textHome)
        bottomSheetManager.setupBottomSheet(inflater, container, binding.bottomsheetButton)

        binding.cardsButton.setOnClickListener {
            findNavController().navigate(R.id.catCardRichMediaDemoFragment)
        }

        binding.carouselButton.setOnClickListener {
            findNavController().navigate(R.id.catCarouselHeroFragment)
        }

        binding.checkboxButton.setOnClickListener {
            findNavController().navigate(R.id.catCheckboxFragment)
        }

        binding.chipButton.setOnClickListener {
            findNavController().navigate(R.id.catChipFragment)
        }

        binding.colorButton.setOnClickListener {
            findNavController().navigate(R.id.catColorFragment)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bottomSheetManager.cleanup()
        actionBarManager.cleanup()
        _binding = null
    }
}