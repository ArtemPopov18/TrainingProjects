package com.popov.permissions.presation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popov.permissions.data.PhotoDao
import com.popov.permissions.entity.Photo
import kotlinx.coroutines.launch

class PhotographViewModel(private val photoDao: PhotoDao) : ViewModel() {

    fun addPhoto(path: String, date: String) {
        viewModelScope.launch {
            Log.d("AAA", "$path $date")
            photoDao.insert(
                Photo(
                    path = path,
                    date = date
                )
            )
        }
    }

}