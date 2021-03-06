package com.example.worldnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.worldnews.R
import com.example.worldnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)



        navController.addOnDestinationChangedListener{controller,destination,arguments ->
            when(destination.id){
                R.id.loginFragment-> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                R.id.newsDetailsFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE

                }
                else ->{
                    binding.bottomNavigationView.visibility = View.VISIBLE

                }
            }

        }
    }
}