package com.example.material_components_demo.ui.home.preference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import com.example.material_components_demo.R

class ThemePreferenceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_theme_preference, container, false)

        // Initialize theme selection buttons
        view.findViewById<View>(R.id.light_button)?.setOnClickListener {
            applyTheme(AppCompatDelegate.MODE_NIGHT_NO)
        }
        view.findViewById<View>(R.id.dark_button)?.setOnClickListener {
            applyTheme(AppCompatDelegate.MODE_NIGHT_YES)
        }
        view.findViewById<View>(R.id.system_default_button)?.setOnClickListener {
            applyTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

        return view
    }

    private fun applyTheme(nightMode: Int) {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate() // Recreate activity to apply theme
    }
}