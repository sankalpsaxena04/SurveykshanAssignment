package com.example.surveykshanassignment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.surveykshanassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
    private val MEDIA_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.READ_MEDIA_AUDIO
        )
    } else {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkAndRequestPermissions()

    }


    fun checkAndRequestPermissions(): Boolean {
        val missingPermissions = PERMISSIONS.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        return if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toTypedArray(),
                Companion.PERMISSION_REQUEST_CODE
            )
            false
        } else {
            true
        }
    }
    fun checkAndRequestMediaPermissions(): Boolean {
        val missingPermissions = MEDIA_PERMISSIONS.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        return if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toTypedArray(), MEDIA_PERMISSION_REQUEST_CODE)
            false
        } else {
            true // All media permissions are already granted
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            AUDIO_PERMISSION_REQUEST_CODE -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    checkAndRequestMediaPermissions()
                    Toast.makeText(this, "Audio permissions granted!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Audio permissions are required to record audio.", Toast.LENGTH_SHORT).show()
                }
            }
            MEDIA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Toast.makeText(this, "Media permissions granted!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Media permissions are required to access files.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        // Permission request codes
        private const val AUDIO_PERMISSION_REQUEST_CODE = 102
        private const val MEDIA_PERMISSION_REQUEST_CODE = 103
        private const val PERMISSION_REQUEST_CODE = 101
    }


}