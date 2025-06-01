package com.example.material_components_demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.example.material_components_demo.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.view.Menu
import android.view.MenuItem

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var actionMode: ActionMode? = null
    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        toggleActionBar()
        toggleBottomSheetDialog(inflater, container)
        return root
    }

    private fun toggleBottomSheetDialog(inflater: LayoutInflater, container: ViewGroup?) {
        // Set up BottomSheetDialog
        bottomSheetDialog = BottomSheetDialog(requireContext())
        // Inflate the simplified XML layout for the BottomSheet
        val bottomSheetView = inflater.inflate(R.layout.cat_bottomsheet_content, container, false)
        bottomSheetDialog?.setContentView(bottomSheetView)

        // Configure BottomSheetBehavior
        val bottomSheetInternal =
            bottomSheetDialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        if (bottomSheetInternal != null) {
            val behavior = BottomSheetBehavior.from(bottomSheetInternal)
            behavior.isDraggable = true // Ensure the bottom sheet is draggable
            behavior.peekHeight = 300 // Set a reasonable peek height (adjust as needed)

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    val stateTextView: TextView =
                        bottomSheetView.findViewById(R.id.bottomsheet_state)
                    when (newState) {
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            stateTextView.text = getString(R.string.cat_bottomsheet_state_expanded)
                        }

                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            stateTextView.text = getString(R.string.cat_bottomsheet_state_collapsed)
                        }

                        BottomSheetBehavior.STATE_DRAGGING -> {
                            stateTextView.text = getString(R.string.cat_bottomsheet_state_dragging)
                        }

                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                            stateTextView.text =
                                getString(R.string.cat_bottomsheet_state_half_expanded)
                        }

                        BottomSheetBehavior.STATE_HIDDEN -> {
                           // TODO()
                        }

                        BottomSheetBehavior.STATE_SETTLING -> {
                           // TODO()
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // Optional: Handle sliding behavior if needed
                }
            })
        }

        // Ensure the drag handle is visible and enabled
        val dragHandle: View = bottomSheetView.findViewById(R.id.drag_handle)
        dragHandle.visibility = View.VISIBLE
        dragHandle.isEnabled = true

        // Button to show the BottomSheet
        val button: Button = binding.bottomsheetButton
        button.setOnClickListener {
            bottomSheetDialog?.show()
        }
    }

    private fun toggleActionBar() {
        val toggleActionModeButton: Button = binding.textHome

        //        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        //        homeViewModel.text.observe(viewLifecycleOwner) {
        //            toggleActionModeButton.text = it
        //        }

        toggleActionModeButton.setOnClickListener {
            if (actionMode == null) {
                actionMode =
                    (activity as AppCompatActivity).startSupportActionMode(actionModeCallback)
                actionMode?.title = "1 selected"
            }
        }
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
        bottomSheetDialog?.dismiss() // Dismiss the dialog to avoid memory leaks
        bottomSheetDialog = null
        _binding = null
    }
}