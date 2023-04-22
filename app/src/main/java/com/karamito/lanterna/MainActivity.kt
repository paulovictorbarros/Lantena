package com.karamito.lanterna

import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.karamito.lanterna.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var state = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)

        supportActionBar!!.hide()

        this.binding.flashlight.setOnClickListener {
            if (!state) {
                binding.flashlight.setImageResource(R.drawable.flashlight_on)
                state = true
                flashlightLight(this.state)

            } else {
                binding.flashlight.setImageResource(R.drawable.flashlight_off)
                state = false
                flashlightLight(this.state)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashlightLight(state: Boolean) {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val cameraId: String?

        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, state)
        } catch (_: Exception) {

        }

    }
}