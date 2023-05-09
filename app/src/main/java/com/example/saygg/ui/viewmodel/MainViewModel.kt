package com.example.saygg.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _title = MutableLiveData<String>();
    val title: LiveData<String> = _title

    private val _imageTitle = MutableLiveData<Int>();
    val imageTitle: LiveData<Int> = _imageTitle

    fun setTitle(title : String){
        _title.value = title
    }

    fun setImage(image : Int){
        _imageTitle.value = image
    }
}