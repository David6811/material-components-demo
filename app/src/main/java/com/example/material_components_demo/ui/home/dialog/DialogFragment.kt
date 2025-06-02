package com.example.material_components_demo.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_main_demo, container, false)
        val dialogLaunchersLayout = view.findViewById<LinearLayout>(R.id.dialog_launcher_buttons_layout)

        // Sample data for dialogs
        val choices = arrayOf("Choice1", "Choice2", "Choice3")
        val choicesInitial = booleanArrayOf(false, true, false)
        val multiLineMessage = StringBuilder().apply {
            val line = getString(R.string.line)
            repeat(100) { i -> append(line).append(i).append("\n") }
        }
        val positiveText = getString(R.string.positive)
        val negativeText = getString(R.string.negative)
        val title = getString(R.string.title)
        val message = getString(R.string.message)
        val longMessage = getString(R.string.long_message)

        // Add dialog launchers
        addDialogLauncher(dialogLaunchersLayout, R.string.app_compat_alert_dialog) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.message_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(message)
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.long_message_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(longMessage)
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_message_3_long_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.long_positive), null)
                .setNegativeButton(getString(R.string.long_negative), null)
                .setNeutralButton(getString(R.string.long_neutral), null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.long_title_message_too_long_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.long_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.too_long_positive), null)
                .setNegativeButton(getString(R.string.too_long_negative), null)
                .setNeutralButton(getString(R.string.too_long_neutral), null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.icon_title_message_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .setIcon(R.drawable.ic_dialogs_24px)
                .show()
        }

//        addDialogLauncher(dialogLaunchersLayout, R.string.icon_title_message_2_actions_centered) {
//            MaterialAlertDialogBuilder(requireContext(), getCenteredTitleThemeOverlay())
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(positiveText, null)
//                .setNegativeButton(negativeText, null)
//                .setIcon(R.drawable.ic_dialogs_24px)
//                .show()
//        }

        addDialogLauncher(dialogLaunchersLayout, R.string.edit_text) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setView(R.layout.edit_text)
                .setPositiveButton(positiveText) { dialog, _ ->
                    val input = (dialog as? androidx.appcompat.app.AlertDialog)?.findViewById<TextView>(android.R.id.text1)
                    Toast.makeText(requireContext(), input?.text, Toast.LENGTH_LONG).show()
                }
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_choices_as_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setPositiveButton(positiveText, null)
                .setItems(choices, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_checkboxes_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setPositiveButton(positiveText) { dialog, _ ->
                    val listView = (dialog as? androidx.appcompat.app.AlertDialog)?.listView
                    val checkedItems = listView?.checkedItemPositions?.let { positions ->
                        choices.filterIndexed { index, _ -> positions.get(index) }.toList()
                    } ?: emptyList()
                    Toast.makeText(requireContext(), checkedItems.toString(), Toast.LENGTH_LONG).show()
                }
                .setNegativeButton(negativeText, null)
                .setMultiChoiceItems(choices, choicesInitial, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_radiobuttons_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setPositiveButton(positiveText) { dialog, _ ->
                    val listView = (dialog as? androidx.appcompat.app.AlertDialog)?.listView
                    val checkedPosition = listView?.checkedItemPosition ?: -1
                    if (checkedPosition != -1) {
                        Toast.makeText(requireContext(), choices[checkedPosition], Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton(negativeText, null)
                .setSingleChoiceItems(choices, 1, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_slider_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setView(R.layout.seekbar_layout)
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_scrolling_2_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(multiLineMessage.toString())
                .setPositiveButton(positiveText, null)
                .setNegativeButton(negativeText, null)
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_scrolling) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(multiLineMessage.toString())
                .show()
        }

        addDialogLauncher(dialogLaunchersLayout, R.string.title_2_short_actions) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setPositiveButton(R.string.short_text_1, null)
                .setNegativeButton(R.string.short_text_2, null)
                .show()
        }

        return view
    }

    private fun addDialogLauncher(
        viewGroup: ViewGroup,
        @StringRes stringResId: Int,
        clickListener: View.OnClickListener
    ) {
        val dialogLauncherButton = MaterialButton(viewGroup.context).apply {
            setOnClickListener(clickListener)
            setText(stringResId)
        }
        viewGroup.addView(dialogLauncherButton)
    }
}