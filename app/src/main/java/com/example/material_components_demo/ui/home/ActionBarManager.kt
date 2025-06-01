package com.example.material_components_demo.ui.home

import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R

class ActionBarManager(private val fragment: Fragment) {

    private var actionMode: ActionMode? = null

    fun setupActionBar(toggleActionModeButton: Button) {
        //        val homeViewModel = ViewModelProvider(fragment).get(HomeViewModel::class.java)
        //        homeViewModel.text.observe(fragment.viewLifecycleOwner) {
        //            toggleActionModeButton.text = it
        //        }

        toggleActionModeButton.setOnClickListener {
            if (actionMode == null) {
                actionMode =
                    (fragment.activity as AppCompatActivity).startSupportActionMode(actionModeCallback)
                actionMode?.title = "1 selected"
            }
        }
    }

    fun cleanup() {
        actionMode?.finish()
        actionMode = null
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
}