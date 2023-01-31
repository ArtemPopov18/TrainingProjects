package com.popov.cleanarchitecture.presation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popov.cleanarchitecture.domain.GetUsefulActivityUseCase
import com.popov.cleanarchitecture.entity.UsefulActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val getUsefulActivityUseCase: GetUsefulActivityUseCase) :
    ViewModel() {

    private val _infoUsefulActivity = Channel<UsefulActivity>()
    val infoUsefulActivity = _infoUsefulActivity.receiveAsFlow()

    fun reloadUsefulActivity() {
        viewModelScope.launch {
            Log.d("AAA", "первый")
            _infoUsefulActivity.send(getUsefulActivityUseCase.execute())
        }
    }
}