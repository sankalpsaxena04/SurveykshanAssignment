package com.example

import android.media.MediaRecorder
import android.os.Environment
import java.io.File
import java.io.IOException

class AudioRecordHelper {

        private var recorder: MediaRecorder? = null
        private var audioFilePath: String? = null

        fun startRecording(): String? {
            val audioFile = createAudioFile() // Create audio file before recording starts
            audioFilePath = audioFile?.absolutePath // Store the file path to reference later

            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(audioFilePath)
                prepare()
                start()
            }

            return audioFilePath // Return the file path
        }

        fun stopRecording() {
            recorder?.apply {
                stop()
                release()
            }
            recorder = null
        }

        private fun createAudioFile(): File? {
            // Ensure a directory for audio recordings exists
            val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "MyRecordings")
            if (!storageDir.exists()) {
                storageDir.mkdirs() // Create directory if it doesn’t exist
            }

            // Generate a unique filename
            return File.createTempFile("audio_recording_${System.currentTimeMillis()}", ".m4a", storageDir)
        }
    }
