package com.popov.geolocation.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.popov.geolocation.R
import com.popov.geolocation.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    private var locationListener: LocationSource.OnLocationChangedListener? = null
    private var map: GoogleMap? = null
    private lateinit var fusedLocation: FusedLocationProviderClient
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var needAnimationCamera = false
    private var needMoveCamera = true
    private val handler = Handler(Looper.getMainLooper())
    private val cameraMovedRunnable = Runnable {
        needMoveCamera = true
    }


    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { it }) {
            startLocation()
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let { location ->
                locationListener?.onLocationChanged(location)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    LatLng(location.latitude, location.latitude),
                    16f
                )
                if (needMoveCamera) {
                    if (needAnimationCamera) {
                        map?.animateCamera(
                            cameraUpdate
                        )
                    } else {
                        needAnimationCamera = true
                        map?.moveCamera(cameraUpdate)
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocation() {
        map?.isMyLocationEnabled = true
        val request = LocationRequest.create()
            .setInterval(1_000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)

        fusedLocation.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun checkPermission() {
        if (REQUIRED_PERMISSION.all { permission ->
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocation()
        } else {
            launcher.launch(REQUIRED_PERMISSION)
        }
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapOverlay.setOnTouchListener { view, motionEvent ->
            handler.removeCallbacks(cameraMovedRunnable)
            needMoveCamera = false
            handler.postDelayed(cameraMovedRunnable, 10_000)
            false
        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.main) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            map = googleMap
            checkPermission()
            with(googleMap.uiSettings) {
                isZoomControlsEnabled = true
                isMyLocationButtonEnabled = true
            }
            googleMap.setLocationSource(object : LocationSource {
                override fun activate(p0: LocationSource.OnLocationChangedListener) {
                    locationListener = p0
                }

                override fun deactivate() {
                    locationListener = null
                }
            })

            viewModel.latLonModel.onEach { listLatLonModel ->
                Log.d("AAA", "$listLatLonModel")
                listLatLonModel.forEach {
                    var attraction = LatLng(it.lat, it.lon)
                    googleMap.addMarker(MarkerOptions().position(attraction).title(it.message))
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//                viewModel.latLonModel.onEach { listLatLonModel ->
//                    Log.d("AAA", "$listLatLonModel")
//                    listLatLonModel.forEach {
//                        var attraction = LatLng(it.lat, it.lon)
//                        googleMap.addMarker(MarkerOptions().position(attraction).title(it.message))
//                    }
//                }
//            }

//            val attractionFirst = LatLng(56.4815, 84.9856)
//            val attractionSecond = LatLng(56.4821, 84.9865)
//            googleMap.addMarker(MarkerOptions().position(attractionFirst).title("Тут ничего нет интересного"))
//            googleMap.addMarker(MarkerOptions().position(attractionSecond).title("И тут тоже ничего нет интересного"))
        }
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    override fun onStop() {
        super.onStop()
        fusedLocation.removeLocationUpdates(locationCallback)
        needAnimationCamera = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        fun newInstance() = MainFragment()
        private val REQUIRED_PERMISSION: Array<String> = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}