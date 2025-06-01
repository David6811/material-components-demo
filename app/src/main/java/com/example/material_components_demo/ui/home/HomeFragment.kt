package com.example.material_components_demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.material_components_demo.R
import com.example.material_components_demo.databinding.FragmentHomeBinding
import androidx.appcompat.view.ActionMode
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val toggleActionModeButton: Button = binding.textHome

//        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            toggleActionModeButton.text = it
//        }

        toggleActionModeButton.setOnClickListener {
            if (actionMode == null) {
                actionMode = (activity as AppCompatActivity).startSupportActionMode(actionModeCallback)
                actionMode?.title = "1 selected"
            }
        }

        return root
    }

    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.contextual_action_bar, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.delete, R.id.more -> {
                    mode?.finish()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}