package com.ozancanguz.distancetrackerapp.ui.fragments.maps

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ozancanguz.distancetrackerapp.R
import com.ozancanguz.distancetrackerapp.databinding.FragmentMapsBinding
import com.ozancanguz.distancetrackerapp.utils.Extension.disable
import com.ozancanguz.distancetrackerapp.utils.Extension.hide
import com.ozancanguz.distancetrackerapp.utils.Extension.show
import com.ozancanguz.distancetrackerapp.utils.Permissions.hasBackgroundLocationPermission
import com.ozancanguz.distancetrackerapp.utils.Permissions.requestBackgroundLocationPermission
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsFragment : Fragment(),OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener,EasyPermissions.PermissionCallbacks{
      private var _binding: FragmentMapsBinding? = null

    private val binding get() = _binding!!

    private lateinit var map:GoogleMap





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.startButton.setOnClickListener {

            onStartButtonClicked()

        }
        binding.stopButton.setOnClickListener {

        }
        binding.resetButton.setOnClickListener {

        }

        return view

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googlemap: GoogleMap) {
        map=googlemap
        map.isMyLocationEnabled = true
        map.setOnMyLocationButtonClickListener(this)

        map.uiSettings.apply {
            isZoomControlsEnabled = false
            isZoomGesturesEnabled = false
            isRotateGesturesEnabled = false
            isTiltGesturesEnabled = false
            isCompassEnabled = false
            isScrollGesturesEnabled = false

        }

    }
    private fun onStartButtonClicked() {
        if (hasBackgroundLocationPermission(requireContext())) {
         //   startCountDown()
            binding.startButton.disable()
            binding.startButton.hide()
            binding.stopButton.show()
        } else {
            requestBackgroundLocationPermission(this)
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestBackgroundLocationPermission(this)
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        onStartButtonClicked()
    }

    override fun onMyLocationButtonClick(): Boolean {
        binding.hintTextView.animate().alpha(0f).duration = 1500
        lifecycleScope.launch {
            delay(2500)
            binding.hintTextView.hide()
            binding.startButton.show()
        }
        return false
    }



}