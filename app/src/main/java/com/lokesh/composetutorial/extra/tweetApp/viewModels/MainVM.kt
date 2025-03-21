package com.lokesh.composetutorial.extra.tweetApp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVM : ViewModel() {
    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> get() = _showDialog

    // Function to update the dialog visibility
    fun updateAirplaneModeStatus() {
        _showDialog.value = true // Show dialog when airplane mode changes
    }

    fun dismissDialog() {
        _showDialog.value = false // Dismiss the dialog
    }
}