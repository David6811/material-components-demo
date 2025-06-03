package com.example.material_components_demo.ui.home.preference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import com.example.material_components_demo.R
import com.google.android.material.button.MaterialButton
import androidx.core.content.ContextCompat

class ThemePreferenceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_theme_preference, container, false)

        // Get buttons
        val lightButton = view.findViewById<MaterialButton>(R.id.light_button)
        val darkButton = view.findViewById<MaterialButton>(R.id.dark_button)
        val systemDefaultButton = view.findViewById<MaterialButton>(R.id.system_default_button)

        // Define colors for selected and unselected states
        val selectedColor = ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_primary40)
        val unselectedColor = ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_neutral_variant60)
        val selectedTextColor = ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_neutral0)
        val unselectedTextColor = ContextCompat.getColor(requireContext(), com.google.android.material.R.color.material_dynamic_neutral10)

        // Function to update button appearance
        fun updateButtonAppearance(selected: MaterialButton) {
            // Reset all buttons to unselected state
            lightButton.run {
                setBackgroundTintList(android.content.res.ColorStateList.valueOf(unselectedColor))
                setTextColor(unselectedTextColor)
                isSelected = false
            }
            darkButton.run {
                setBackgroundTintList(android.content.res.ColorStateList.valueOf(unselectedColor))
                setTextColor(unselectedTextColor)
                isSelected = false
            }
            systemDefaultButton.run {
                setBackgroundTintList(android.content.res.ColorStateList.valueOf(unselectedColor))
                setTextColor(unselectedTextColor)
                isSelected = false
            }
            // Set selected button appearance
            selected.run {
                setBackgroundTintList(android.content.res.ColorStateList.valueOf(selectedColor))
                setTextColor(selectedTextColor)
                isSelected = true
            }
        }

        // Get current theme and set selected state
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_NO -> updateButtonAppearance(lightButton)
            AppCompatDelegate.MODE_NIGHT_YES -> updateButtonAppearance(darkButton)
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, AppCompatDelegate.MODE_NIGHT_UNSPECIFIED ->
                updateButtonAppearance(systemDefaultButton)
        }

        // Initialize theme selection buttons
        lightButton.setOnClickListener {
            applyTheme(AppCompatDelegate.MODE_NIGHT_NO)
            updateButtonAppearance(lightButton)
        }
        darkButton.setOnClickListener {
            applyTheme(AppCompatDelegate.MODE_NIGHT_YES)
            updateButtonAppearance(darkButton)
        }
        systemDefaultButton.setOnClickListener {
            applyTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            updateButtonAppearance(systemDefaultButton)
        }

        return view
    }

    private fun applyTheme(nightMode: Int) {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate() // Recreate activity to apply theme
    }
}