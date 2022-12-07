package com.terabyte.navigationdrawerstepbystepexplanation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.terabyte.navigationdrawerstepbystepexplanation.databinding.ActivityMainBinding
import com.terabyte.navigationdrawerstepbystepexplanation.fragment.CameraFragment
import com.terabyte.navigationdrawerstepbystepexplanation.fragment.GalleryFragment
import com.terabyte.navigationdrawerstepbystepexplanation.fragment.ManageFragment
import com.terabyte.navigationdrawerstepbystepexplanation.fragment.SlideshowFragment

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        binding.navView.setCheckedItem(R.id.navCamera)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, CameraFragment())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = when(item.itemId) {
            R.id.navCamera -> CameraFragment()
            R.id.navGallery -> GalleryFragment()
            R.id.navSlideshow -> SlideshowFragment()
            R.id.navManage -> ManageFragment()
            else -> CameraFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment)
            .commit()

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}