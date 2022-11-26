package com.example.mangalist

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.luminance
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mangalist.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(com.example.mangalist.R.id.navFragment) as NavHostFragment? ?: return
        val navController = host.navController

        binding.navView.setupWithNavController(navController)
        val appBarConfiguration =
            AppBarConfiguration(navController.graph, drawerLayout = binding.drawerLayout)
        appBarConfiguration.openableLayout?.isOpen
        binding.toolbar.setNavigationOnClickListener { Log.d("KEK", "onCreate: QWE") }
        // для затемнения при скроле
        binding.drawerLayout.setScrimColor(Color.GRAY)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        supportActionBar?.title = "asd"
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.drawer_menu, menu);
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            R.id.exitFragment -> {
//                this.finishAffinity();
//                return true
//            }
//            else -> return false
//
//        }
//    }
}