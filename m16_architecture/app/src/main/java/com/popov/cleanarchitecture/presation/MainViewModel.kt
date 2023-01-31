package com.popov.cleanarchitecture.presation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popov.cleanarchitecture.domain.GetUsefulActivityUseCase
import com.popov.cleanarchitecture.entity.UsefulActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val getUsefulActivityUseCase: GetUsefulActivityUseCase) : ViewModel() {

    private val _infoUsefulActivity = Channel<UsefulActivity>()
    val infoUsefulActivity = _infoUsefulActivity.receiveAsFlow()

    fun reloadUsefulActivity(){
        viewModelScope.launch {
            _infoUsefulActivity.send(getUsefulActivityUseCase.execute())
        }
    }
}