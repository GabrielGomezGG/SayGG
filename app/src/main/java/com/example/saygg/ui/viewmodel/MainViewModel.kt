package com.example.saygg.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _title = MutableLiveData<String>();
    val title: LiveData<String> = _title

    private val _imageTitle = MutableLiveData<String>("https://cdn-images-1.medium.com/v2/resize:fit:1200/1*Dv4gnBhPF8PtDcH-gjYgEQ.png");
    val imageTitle: LiveData<String> = _imageTitle

    fun setTitle(title : String){
        _title.value = title
    }

    fun setImage(image : String){
        _imageTitle.value = image
    }
}