package com.example.material_components_demo.ui.home

import SimpleCarouselAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.material_components_demo.R
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy

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

        recyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())

        recyclerView.adapter = SimpleCarouselAdapter(
            listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)
        )
    }
}