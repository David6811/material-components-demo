package com.example.material_components_demo.ui.home.adaptive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R

class DetailFragment : Fragment() {
    companion object {
        private const val ARG_EMAIL = "email"
        fun newInstance(email: Email): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_EMAIL, email)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_adaptive_list_view_detail_fragment, container, false)
        @Suppress("DEPRECATION") // Safe for minSdk 24
        val email = arguments?.getParcelable<Email>(ARG_EMAIL)

        // Safely update UI with email data
        view.findViewById<TextView>(R.id.email_title).text = email?.subject ?: "No Subject"
        view.findViewById<TextView>(R.id.email_content).text = email?.preview ?: "No Content"

        return view
    }
}