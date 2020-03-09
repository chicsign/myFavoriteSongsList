package com.lonnie.chicsign.myfavoritesongs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lonnie.chicsign.myfavoritesongs.model.Info
import com.lonnie.chicsign.myfavoritesongs.network.data.Repo

class MainViewModel : ViewModel() {

    private val repo = Repo()

    fun fetchInfo(): LiveData<MutableList<Info>> {
        val mutableData = MutableLiveData<MutableList<Info>>()
        repo.getInfo().observeForever { songList ->
            mutableData.value = songList
        }

        return mutableData
    }
}