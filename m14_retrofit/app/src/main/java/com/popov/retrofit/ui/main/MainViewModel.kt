package com.popov.retrofit.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.popov.retrofit.People
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    fun getData() {
        _state.value = State.Loading
//        var urlPicture = ""
//        var dataPeople = ""
        val list = repository.getData()
        Log.d("AAA", "вьюмодель $list")
        if (list.lastIndex == 1){
            _state.value = State.Success(list[0], list[1])
        } else {
            _state.value = State.Error(list[0])
        }

//        val apiInterface = ApiInterface.create().getPerson()
//
//        apiInterface.enqueue( object : Callback<People> {
//            override fun onResponse(call: Call<People>, response: Response<People>) {
//                val per = response.body()
//                if (per != null) {
//                    dataPeople = per.results[0].toString()
//                }
//                if (per != null) {
//                    urlPicture = per.results[0].picture.getURL()
//                }
//                Log.d("AAA", "${response.body()}")
//                _state.value = State.Success(dataPeople, urlPicture)
//            }
//
//            override fun onFailure(call: Call<People>, t: Throwable) {
//                _state.value = State.Error("${t.message}")
//                Log.d("AAA", "onFailure ${t.message}")
//            }
//        })
    }
}