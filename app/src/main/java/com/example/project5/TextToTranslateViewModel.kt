package com.example.project5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class TextToTranslateViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val message = MutableLiveData<String>()

    fun sendMessage(text: String){
        message.value = text
    }

}