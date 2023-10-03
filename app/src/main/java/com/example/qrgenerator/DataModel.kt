package com.example.qrgenerator


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class DataModel : ViewModel() {

//    val result: MutableLiveData<String> = MutableLiveData<String>()

    private val mutableSelectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = mutableSelectedItem

    fun selectItem(result: String) {
        mutableSelectedItem.value = result
    }

}