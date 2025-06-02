package com.example.material_components_demo.ui.home.adaptive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.material_components_demo.R

class EmailAdapter(
    private val emails: List<Email>,
    private val onItemClick: (Email) -> Unit
) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sender: TextView = view.findViewById(R.id.sender_title)
        val subject: TextView = view.findViewById(R.id.email_title)
        val preview: TextView = view.findViewById(R.id.email_preview)
        val timestamp: TextView = view.findViewById(R.id.email_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_adaptive_list_view_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = emails[position]
        holder.sender.text = email.sender
        holder.subject.text = email.subject
        holder.preview.text = email.preview
        holder.timestamp.text = email.timestamp
        holder.itemView.setOnClickListener { onItemClick(email) }
    }

    override fun getItemCount(): Int = emails.size
}