package dev.mynumber.connect.android.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReaderViewModel: ViewModel() {
    private val _pin = MutableLiveData("")
    val pin: LiveData<String> = _pin

    fun setPin(pin: String) {
        _pin.value = pin
    }

    companion object {
        private var instance: ReaderViewModel? = null;
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ReaderViewModel().also {instance = it}
        }
    }
}