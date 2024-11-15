package com.example.surveykshanassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.AudioRecordHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor():ViewModel() {
    private val _gender = MutableLiveData<String?>()
    val gender: LiveData<String?> = _gender
    private val audioRecorder = AudioRecordHelper()

    private val _age = MutableLiveData<Int?>()
    val age: LiveData<Int?> = _age
    private val _selfie = MutableLiveData<String?>()
    val selfie: LiveData<String?> = _selfie

    private val _isRecording = MutableLiveData<Boolean>()
    val isRecording: LiveData<Boolean> get() = _isRecording

    private val _audioFilePath = MutableLiveData<String?>()
    val audioFilePath: LiveData<String?> get() = _audioFilePath

    fun startRecording() {
        _audioFilePath.value = audioRecorder.startRecording()
        audioRecorder.startRecording()
        _isRecording.value = true
    }

    fun stopRecording() {
        audioRecorder.stopRecording()
        _isRecording.value = false
    }

    fun setGender(gender:String?){
        _gender.postValue(gender)
    }

    fun setAge(age:Int?){
        _age.postValue(age)
    }

    fun setSelfie(selfie:String?){
        _selfie.postValue(selfie)
    }


}