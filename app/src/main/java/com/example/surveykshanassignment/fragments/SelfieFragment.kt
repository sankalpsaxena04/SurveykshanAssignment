package com.example.surveykshanassignment.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.surveykshanassignment.GlobalUtils
import com.example.surveykshanassignment.MainActivity
import com.example.surveykshanassignment.MainViewModel
import com.example.surveykshanassignment.R
import com.example.surveykshanassignment.databinding.FragmentSelfieBinding
import java.io.File

class SelfieFragment : Fragment() {

    private var _binding: FragmentSelfieBinding? = null
    private val binding get() = _binding!!
    private val CAMERA_PERMISSION_REQUEST = 100
    private val REQUEST_USER_IMAGE_CAPTURE = 1
    private var capturedUserImageUri: Uri? = null
    private val util: GlobalUtils.EasyElements by lazy {
        GlobalUtils.EasyElements(requireActivity())
    }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelfieBinding.inflate(inflater,container,false)
        binding.btnNext.setOnClickListener {
            if(viewModel.selfie.value != null){
                findNavController().navigate(R.id.action_selfieFragment_to_submitFragment)
            }
            else{
                Toast.makeText(requireContext(), "Please take a selfie", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.selfie.observe(viewLifecycleOwner){
            if(it != null){
                binding.selfieIV.setImageURI(Uri.parse(it))
            }
        }
        binding.cameraBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_REQUEST
                )
                util.showSnackbar(binding.root,"Camera permission is required",2000)
            } else {
                pickUserImage()
            }
        }

        binding.btnPrevious.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun pickUserImage() {
        val options = arrayOf<CharSequence>("Take Selfie", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Click your selfie in a well lit environment")
        builder.setItems(options) { dialog, item ->
            when (options[item]) {
                "Take Selfie" -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                    capturedUserImageUri = FileProvider.getUriForFile(
                        requireContext(),
                        "${requireContext().packageName}.provider",
                        createImageFile("User_Selfie")
                    )

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, capturedUserImageUri)
                    startActivityForResult(intent, REQUEST_USER_IMAGE_CAPTURE)
                }

                "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }
        builder.show()
    }
    private fun createImageFile(type: String): File {
        val storageDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        return File(storageDir, "Surveykshan-Snap-${type}-${System.currentTimeMillis()}.jpeg")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_USER_IMAGE_CAPTURE -> {
                    capturedUserImageUri?.let { uri ->
                        viewModel.setSelfie(uri.toString())
                        binding.selfieIV.setImageURI(uri)
                        MediaScannerConnection.scanFile(
                            requireContext(),
                            arrayOf(uri.path),
                            arrayOf("image/jpeg"),
                            null
                        )
                    }
                }
            }
        }
    }


}