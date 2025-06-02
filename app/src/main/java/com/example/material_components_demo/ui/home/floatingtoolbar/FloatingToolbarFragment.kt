package com.example.material_components_demo.ui.home.floatingtoolbar

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import java.util.Random

class FloatingToolbarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_floating_toolbar_fragment, container, false)

        // Set up the toolbar
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        // Find the TextView to apply formatting
        val bodyText = view.findViewById<TextView>(R.id.body_text)
            ?: return view // Early return if TextView is not found

        // Initialize LinearLayouts for toolbars with different orientations
        val floatingToolbarBottom = createFloatingToolbar(inflater, view as ViewGroup, R.id.floating_toolbar_bottom, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
        val floatingToolbarLeft = createFloatingToolbar(inflater, view, R.id.floating_toolbar_left, Gravity.START or Gravity.CENTER_VERTICAL)
        val floatingToolbarRight = createFloatingToolbar(inflater, view, R.id.floating_toolbar_right, Gravity.END or Gravity.CENTER_VERTICAL)
        val floatingToolbars = listOf(floatingToolbarBottom, floatingToolbarLeft, floatingToolbarRight)

        // Initialize bold buttons
        val boldButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_bold)
        boldButtons.forEach { button ->
            button.addOnCheckedChangeListener { _, isChecked ->
                val typeface = bodyText.typeface ?: Typeface.DEFAULT
                bodyText.typeface = Typeface.create(
                    typeface,
                    when {
                        isChecked && typeface.isItalic -> Typeface.BOLD_ITALIC
                        isChecked -> Typeface.BOLD
                        typeface.isItalic -> Typeface.ITALIC
                        else -> Typeface.NORMAL
                    }
                )
                propagateCheckedButtonState(boldButtons, isChecked)
            }
        }

        // Initialize italic buttons
        val italicButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_italic)
        italicButtons.forEach { button ->
            button.addOnCheckedChangeListener { _, isChecked ->
                val typeface = bodyText.typeface ?: Typeface.DEFAULT
                bodyText.typeface = Typeface.create(
                    typeface,
                    when {
                        isChecked && typeface.isBold -> Typeface.BOLD_ITALIC
                        isChecked -> Typeface.ITALIC
                        typeface.isBold -> Typeface.BOLD
                        else -> Typeface.NORMAL
                    }
                )
                propagateCheckedButtonState(italicButtons, isChecked)
            }
        }

        // Initialize underline buttons
        val underlineButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_underlined)
        underlineButtons.forEach { button ->
            button.addOnCheckedChangeListener { _, isChecked ->
                val paintFlags = bodyText.paintFlags
                bodyText.paintFlags = if (isChecked) {
                    paintFlags or Paint.UNDERLINE_TEXT_FLAG
                } else {
                    paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
                }
                propagateCheckedButtonState(underlineButtons, isChecked)
            }
        }

        // Initialize text color buttons
        val colorTextButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_color_text)
        colorTextButtons.forEach { button ->
            button.setOnClickListener { bodyText.setTextColor(getRandomColor()) }
        }

        // Initialize background color buttons
        val colorFillButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_color_fill)
        colorFillButtons.forEach { button ->
            button.setOnClickListener { bodyText.setBackgroundColor(getRandomColor()) }
        }

        // Initialize strikethrough buttons
        val strikethroughButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_strikethrough)
        strikethroughButtons.forEach { button ->
            button.addOnCheckedChangeListener { _, isChecked ->
                val paintFlags = bodyText.paintFlags
                bodyText.paintFlags = if (isChecked) {
                    paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                propagateCheckedButtonState(strikethroughButtons, isChecked)
            }
        }

        // Initialize alignment buttons
        val leftAlignButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_left_align)
        leftAlignButtons.forEach { button ->
            button.setOnClickListener { bodyText.gravity = Gravity.START }
        }

        val centerAlignButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_center_align)
        centerAlignButtons.forEach { button ->
            button.setOnClickListener { bodyText.gravity = Gravity.CENTER_HORIZONTAL }
        }

        val rightAlignButtons = initializeFormatButtons(floatingToolbars, R.id.floating_toolbar_button_right_align)
        rightAlignButtons.forEach { button ->
            button.setOnClickListener { bodyText.gravity = Gravity.END }
        }

        // Initialize orientation buttons
        val toggleGroup = view.findViewById<MaterialButtonToggleGroup>(R.id.toggle_group)
        initializeOrientationButton(view, floatingToolbars, R.id.bottom_button, R.id.floating_toolbar_bottom, toggleGroup)
        initializeOrientationButton(view, floatingToolbars, R.id.left_button, R.id.floating_toolbar_left, toggleGroup)
        initializeOrientationButton(view, floatingToolbars, R.id.right_button, R.id.floating_toolbar_right, toggleGroup)

        // Set initial visibility to bottom toolbar
        toggleGroup?.check(R.id.bottom_button)
        updateFloatingToolbarVisibilities(floatingToolbars, R.id.floating_toolbar_bottom)

        return view
    }

    private fun createFloatingToolbar(
        inflater: LayoutInflater,
        parent: ViewGroup,
        id: Int,
        gravity: Int
    ): LinearLayout {
        val toolbar = inflater.inflate(R.layout.cat_floating_toolbar_content, parent, false) as LinearLayout
        toolbar.id = id
        // Default visibility: only bottom toolbar visible
        toolbar.visibility = if (id == R.id.floating_toolbar_bottom) View.VISIBLE else View.GONE
        // Set a background color for visibility
        toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_100))

        // Use CoordinatorLayout.LayoutParams with adjusted gravity
        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.WRAP_CONTENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            this.gravity = gravity
            // Ensure vertical positioning (top-aligned for left/right)
            anchorGravity = Gravity.TOP
            setMargins(16, 16, 16, 16) // Adjust margins as needed
        }
        toolbar.layoutParams = params

        parent.addView(toolbar)
        return toolbar
    }

    private fun initializeOrientationButton(
        view: View,
        floatingToolbars: List<LinearLayout>,
        buttonId: Int,
        floatingToolbarId: Int,
        toggleGroup: MaterialButtonToggleGroup?
    ) {
        val orientationButton = view.findViewById<MaterialButton>(buttonId)
        orientationButton?.setOnClickListener {
            toggleGroup?.check(buttonId)
            updateFloatingToolbarVisibilities(floatingToolbars, floatingToolbarId)
        }
    }

    private fun updateFloatingToolbarVisibilities(
        floatingToolbars: List<LinearLayout>,
        visibleFloatingToolbarId: Int
    ) {
        floatingToolbars.forEach { toolbar ->
            toolbar.visibility = if (toolbar.id == visibleFloatingToolbarId) View.VISIBLE else View.GONE
        }
    }

    private fun initializeFormatButtons(
        floatingToolbars: List<LinearLayout>,
        formatButtonId: Int
    ): List<MaterialButton> {
        val formatButtons = mutableListOf<MaterialButton>()
        floatingToolbars.forEach { toolbar ->
            toolbar.findViewById<MaterialButton>(formatButtonId)?.let { button ->
                formatButtons.add(button)
            }
        }
        return formatButtons
    }

    private fun propagateCheckedButtonState(buttons: List<MaterialButton>, isChecked: Boolean) {
        buttons.forEach { button ->
            button.isChecked = isChecked
        }
    }

    private fun getRandomColor(): Int {
        val random = Random()
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}