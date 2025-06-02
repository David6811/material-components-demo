package com.example.material_components_demo.ui.home.adaptive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.material_components_demo.R

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_adaptive_list_view_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.email_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = EmailAdapter(getSampleEmails()) { email ->
            // Handle item click to show detail fragment
            showDetailFragment(email)
        }
        return view
    }

    private fun getSampleEmails(): List<Email> {
        // Sample data, replace with your data source
        return listOf(
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
            Email("John Doe", "Email Title 1", "These readers have opted in to receive email notifications for your stories. Learn more.", "10:00 AM"),
        )
    }

    private fun showDetailFragment(email: Email) {
        val fragment = DetailFragment.newInstance(email)
        //val isLargeScreen = requireActivity().findViewById<View>(R.id.list_view_detail_fragment_container) != null
        val isLargeScreen = false
        if (isLargeScreen) {
            // Replace detail fragment in side-by-side layout
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.list_view_detail_fragment_container, fragment)
                .commit()
        } else {
            // Navigate to detail fragment, replacing list fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.list_view_fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}

// data class Email(val sender: String, val subject: String, val preview: String, val timestamp: String)