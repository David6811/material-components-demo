package com.example.material_components_demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.materialswitch.MaterialSwitch

class ChipFragment : Fragment() {

    private lateinit var singleSelectionSwitch: MaterialSwitch

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_chip_group_fragment, container, false)

        // Initialize UI components
        singleSelectionSwitch = view.findViewById(R.id.single_selection)
        val selectionRequiredSwitch = view.findViewById<MaterialSwitch>(R.id.selection_required)
        val reflowGroup = view.findViewById<ChipGroup>(R.id.reflow_group)
        val scrollGroup = view.findViewById<ChipGroup>(R.id.scroll_group)
        val fab = view.findViewById<FloatingActionButton>(R.id.cat_chip_group_refresh)

        // Set up listeners for switches
        singleSelectionSwitch.setOnCheckedChangeListener { _, isChecked ->
            reflowGroup.setSingleSelection(isChecked)
            scrollGroup.setSingleSelection(isChecked)
            initChipGroup(reflowGroup, false)
            initChipGroup(scrollGroup, true)
        }

        selectionRequiredSwitch.setOnCheckedChangeListener { buttonView: CompoundButton, isChecked: Boolean ->
            reflowGroup.setSelectionRequired(isChecked)
            scrollGroup.setSelectionRequired(isChecked)
            initChipGroup(reflowGroup, false)
            initChipGroup(scrollGroup, true)
        }

        // Initialize chip groups
        initChipGroup(reflowGroup, false)
        initChipGroup(scrollGroup, true)

        // Set up FAB click listener
        fab.setOnClickListener {
            reflowGroup.setSingleLine(true)
            initChipGroup(reflowGroup, false)
            initChipGroup(scrollGroup, true)
        }

        return view
    }

    // For basic Chips view
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.cat_chip_content, container, false)
//        return view
//    }

    @LayoutRes
    private fun getChipGroupContent(): Int {
        return R.layout.cat_chip_group_fragment
    }

    @LayoutRes
    private fun getChipGroupItem(singleSelection: Boolean): Int {
        return if (singleSelection) {
            R.layout.cat_chip_group_item_choice
        } else {
            R.layout.cat_chip_group_item_filter
        }
    }

    private fun initChipGroup(chipGroup: ChipGroup, showMenu: Boolean) {
        chipGroup.removeAllViews()

        // Add "View All" chip
        val viewAllChip = Chip(chipGroup.context).apply {
            text = resources.getString(R.string.cat_chip_text_all)
            setChipIconResource(R.drawable.ic_drawer_menu_open_24px)
            chipIconTint = textColors
            isChipIconVisible = true
            isCheckable = !showMenu
        }
        chipGroup.addView(viewAllChip)

        // Set up popup menu for scroll group
        val menu = if (showMenu) {
            PopupMenu(viewAllChip.context, viewAllChip).apply {
                viewAllChip.setOnClickListener { show() }
            }
        } else {
            viewAllChip.setOnClickListener {
                chipGroup.setSingleLine(!chipGroup.isSingleLine())
                viewAllChip.isChecked = !chipGroup.isSingleLine()
                chipGroup.requestLayout()
            }
            null
        }

        // Add chips dynamically
        val singleSelection = singleSelectionSwitch.isChecked()
        val textArray = resources.getStringArray(R.array.cat_chip_group_text_array)
        textArray.forEachIndexed { index, text ->
            val chip = LayoutInflater.from(context).inflate(
                getChipGroupItem(singleSelection),
                chipGroup,
                false
            ) as Chip
            chip.id = index
            chip.text = text
            chipGroup.addView(chip)

            if (menu != null) {
                menu.menu.add(Menu.NONE, index, index, text)
                menu.menu.setGroupCheckable(Menu.NONE, true, singleSelection)
                menu.setOnMenuItemClickListener { menuItem ->
                    val targetChip = chipGroup.getChildAt(menuItem.order + 1) as Chip
                    targetChip.isChecked = !targetChip.isChecked
                    menuItem.isChecked = targetChip.isChecked
                    true
                }
                chip.isCloseIconVisible = false
                chip.setOnCheckedChangeListener { _, isChecked ->
                    menu.menu.getItem(chip.id).isChecked = isChecked
                }
            } else {
                chip.isCloseIconVisible = true
                chip.setOnCloseIconClickListener { chipGroup.removeView(chip) }
            }
        }
    }
}