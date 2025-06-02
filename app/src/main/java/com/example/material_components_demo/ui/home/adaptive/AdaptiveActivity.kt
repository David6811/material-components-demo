package com.example.material_components_demo.ui.home.adaptive

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.material_components_demo.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView

class AdaptiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_adaptive_list_view_activity)

        // Set a custom title for the app bar
        supportActionBar?.title = "Adaptive Demo"

        // Determine screen size using DisplayMetrics
        val displayMetrics = resources.displayMetrics
        val widthPx = displayMetrics.widthPixels
        val density = displayMetrics.density
        val widthDp = widthPx / density
        Log.d("AdaptiveActivity", "Screen width: $widthDp dp")

        // Adjust UI based on screen size
        val navDrawer: NavigationView = findViewById(R.id.nav_drawer)
        val navRail: NavigationRailView = findViewById(R.id.nav_rail)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val modalNavDrawer: NavigationView = findViewById(R.id.modal_nav_drawer)

        when {
            widthDp >= 1240 -> {
                navDrawer.visibility = View.VISIBLE
                navRail.visibility = View.GONE
                bottomNav.visibility = View.GONE
                fab.visibility = View.GONE
                modalNavDrawer.visibility = View.GONE
                loadFragments(true)
            }
            widthDp >= 840 -> {
                navDrawer.visibility = View.GONE
                navRail.visibility = View.VISIBLE
                bottomNav.visibility = View.GONE
                fab.visibility = View.GONE
                modalNavDrawer.visibility = View.GONE
                loadFragments(true)
            }
            else -> {
                navDrawer.visibility = View.GONE
                navRail.visibility = View.GONE
                bottomNav.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
                modalNavDrawer.visibility = View.VISIBLE
                loadFragments(false)
            }
        }

        // Set up navigation listeners
        navDrawer.setNavigationItemSelectedListener { menuItem ->
            handleNavigation(menuItem.itemId)
            findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout).closeDrawer(
                GravityCompat.START)
            true
        }

        navRail.setOnItemSelectedListener { menuItem ->
            handleNavigation(menuItem.itemId)
            true
        }

        bottomNav.setOnItemSelectedListener { menuItem ->
            handleNavigation(menuItem.itemId)
            true
        }

        modalNavDrawer.setNavigationItemSelectedListener { menuItem ->
            handleNavigation(menuItem.itemId)
            findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout).closeDrawer(GravityCompat.START)
            true
        }

        // FAB click listener
        fab.setOnClickListener {
            Toast.makeText(this, "FAB clicked! Create a new email.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadFragments(isLargeScreen: Boolean) {
        val listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_view_fragment_container, listFragment)
            .commit()

        if (isLargeScreen) {
            // Load a default detail fragment for large/medium screens
            val defaultEmail = Email("Default Sender", "Default Subject", "Default Preview", "12:00 PM")
            val detailFragment = DetailFragment.newInstance(defaultEmail)
            supportFragmentManager.beginTransaction()
                .replace(R.id.list_view_detail_fragment_container, detailFragment)
                .commit()
        }
    }

    private fun handleNavigation(itemId: Int) {
        val message = when (itemId) {
            R.id.action_page_1 -> "Page 1 selected"
            R.id.action_page_2 -> "Page 2 selected"
            R.id.action_page_3 -> "Page 3 selected"
            R.id.action_page_4 -> "Page 4 selected"
            else -> "Unknown item selected"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}