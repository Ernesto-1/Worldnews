package com.example.worldnews.ui.loginFragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.worldnews.R
import com.example.worldnews.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val LOCATION_REQUEST_CODE = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.btnEmpezar.setOnClickListener {
            checkLocationPermision()
        }
    }

    private fun checkLocationPermision() {
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION ) }
            != PackageManager.PERMISSION_GRANTED) {
            //El permiso no está aceptado.
            requestLocationPermission()

        } else {
            //El permiso está aceptado.
            findNavController().navigate(R.id.action_loginFragment_to_newsFragment)
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putInt(getString(R.string.saved_high_score_key), Location.CONTENTS_FILE_DESCRIPTOR)
                apply()
            }
        }

    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            //El usuario ya ha rechazado el permiso anteriormente, debemos informarle que vaya a ajustes.
            binding.errorMessage.visibility = View.VISIBLE
            binding.btnEmpezar.visibility = View.GONE
        } else {
            //El usuario nunca ha aceptado ni rechazado, así que le pedimos que acepte el permiso.
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
        }
    }


}