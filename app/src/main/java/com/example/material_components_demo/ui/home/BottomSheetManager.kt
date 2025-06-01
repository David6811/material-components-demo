package com.example.material_components_demo.ui.home
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetManager(private val fragment: Fragment) {

    private var bottomSheetDialog: BottomSheetDialog? = null

    fun setupBottomSheet(inflater: LayoutInflater, container: ViewGroup?, triggerButton: Button) {
        // Set up BottomSheetDialog
        bottomSheetDialog = BottomSheetDialog(fragment.requireContext())
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
                            stateTextView.text = fragment.getString(R.string.cat_bottomsheet_state_expanded)
                        }

                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            stateTextView.text = fragment.getString(R.string.cat_bottomsheet_state_collapsed)
                        }

                        BottomSheetBehavior.STATE_DRAGGING -> {
                            stateTextView.text = fragment.getString(R.string.cat_bottomsheet_state_dragging)
                        }

                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                            stateTextView.text =
                                fragment.getString(R.string.cat_bottomsheet_state_half_expanded)
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
        triggerButton.setOnClickListener {
            bottomSheetDialog?.show()
        }
    }

    fun cleanup() {
        bottomSheetDialog?.dismiss()
        bottomSheetDialog = null
    }
}