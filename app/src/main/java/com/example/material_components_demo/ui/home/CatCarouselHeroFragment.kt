package com.example.material_components_demo.ui.home

import SimpleCarouselAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.material_components_demo.R
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.slider.Slider

class CatCarouselHeroFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cat_carousel_hero_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.hero_start_carousel_recycler_view)
        val debugSwitch = view.findViewById<MaterialSwitch>(R.id.debug_switch)
        val drawDividersSwitch = view.findViewById<MaterialSwitch>(R.id.draw_dividers_switch)
        val enableFlingSwitch = view.findViewById<MaterialSwitch>(R.id.enable_fling_switch)
        val itemCountDropdown = view.findViewById<AutoCompleteTextView>(R.id.item_count_dropdown)
        val positionSlider = view.findViewById<Slider>(R.id.position_slider)
        val startAlignButton = view.findViewById<RadioButton>(R.id.start_align)
        val centerAlignButton = view.findViewById<RadioButton>(R.id.center_align)

        // Divider
        val dividerDecoration = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.HORIZONTAL)

        // Carousel LayoutManager
        val carouselLayoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        recyclerView.layoutManager = carouselLayoutManager
        recyclerView.setNestedScrollingEnabled(false)

        // 初始化数据
        val imageList = listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)
        val adapter = SimpleCarouselAdapter(imageList)
        recyclerView.adapter = adapter

        // Divider 开关
        drawDividersSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                recyclerView.addItemDecoration(dividerDecoration)
            } else {
                recyclerView.removeItemDecoration(dividerDecoration)
            }
        }

        // SnapHelper (Fling 开关)
        val snapHelper = CarouselSnapHelper(false)
        val snapHelperWithFling = CarouselSnapHelper(true)
        enableFlingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                snapHelper.attachToRecyclerView(null)
                snapHelperWithFling.attachToRecyclerView(recyclerView)
            } else {
                snapHelperWithFling.attachToRecyclerView(null)
                snapHelper.attachToRecyclerView(recyclerView)
            }
        }
        snapHelper.attachToRecyclerView(recyclerView) // 默认 attach 无 fling

        // 对齐方式
        startAlignButton.setOnClickListener {
            carouselLayoutManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_START)
        }
        centerAlignButton.setOnClickListener {
            carouselLayoutManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_CENTER)
        }

        // Item 数量下拉框（字符串数组需在 strings.xml 预设好）
        itemCountDropdown.setOnItemClickListener { _, _, position, _ ->
            val count = position + 1 // position 从 0 开始
            val newList = imageList.take(count.coerceAtMost(imageList.size))
            adapter.updateItems(newList)

            // 更新 slider 范围
            positionSlider.valueFrom = 1f
            positionSlider.valueTo = newList.size.toFloat()
            positionSlider.value = 1f
            positionSlider.isEnabled = newList.size > 1
        }

        // 滑动条控制 RecyclerView 滚动
        positionSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                val target = slider.value.toInt() - 1
                recyclerView.smoothScrollToPosition(target)
            }
        })
    }
}