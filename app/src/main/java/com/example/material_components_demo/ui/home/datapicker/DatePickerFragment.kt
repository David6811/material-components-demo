package com.example.material_components_demo.ui.home.datapicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar

class DatePickerFragment : Fragment() {

    private lateinit var snackBar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.picker_main_demo, container, false)

        // Initialize SnackBar
        snackBar = Snackbar.make(
            container ?: view,
            R.string.cat_picker_no_action,
            Snackbar.LENGTH_LONG
        )

        // Find views
        val launchButton = view.findViewById<MaterialButton>(R.id.cat_picker_launch_button)
        val selectionModeGroup = view.findViewById<RadioGroup>(R.id.cat_picker_date_selector_group)

        launchButton.setOnClickListener {
            // Get the selected radio button ID
            val selectionMode = selectionModeGroup.checkedRadioButtonId

            // Create the date picker builder based on selection mode
            val builder = when (selectionMode) {
                R.id.cat_picker_date_selector_single -> {
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                }
                R.id.cat_picker_date_selector_range -> {
                    MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Select Date Range")
                        .setSelection(
                            androidx.core.util.Pair(
                                MaterialDatePicker.todayInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds()
                            )
                        )
                }
                else -> MaterialDatePicker.Builder.datePicker() // Fallback to single date picker
            }

            // Build and show the date picker
            val picker = builder.build()
            addSnackBarListeners(picker)
            picker.show(childFragmentManager, picker.toString())
        }

        return view
    }

    private fun addSnackBarListeners(picker: MaterialDatePicker<*>) {
        // Handle positive button click (OK)
        picker.addOnPositiveButtonClickListener {
            val message = when (picker.selection) {
                is Long -> picker.headerText // Single date
                is androidx.core.util.Pair<*, *> -> picker.headerText // Date range
                else -> "Date selected"
            }
            snackBar.setText(message)
            snackBar.show()
        }

        // Handle negative button click (Cancel)
        picker.addOnNegativeButtonClickListener {
            snackBar.setText("Date selection canceled")
            snackBar.show()
        }

        // Handle dialog cancellation (e.g., back button)
        picker.addOnCancelListener {
            snackBar.setText("Date picker dismissed")
            snackBar.show()
        }
    }
}