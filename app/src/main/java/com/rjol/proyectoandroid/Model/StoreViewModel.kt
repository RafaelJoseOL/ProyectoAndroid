package com.rjol.proyectoandroid.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjol.proyectoandroid.Repository.StoreRepo

class StoreViewModel : ViewModel() {

    private val repo : StoreRepo
    private val _allStores = MutableLiveData<List<Store>>()
    val allStores : LiveData<List<Store>> = _allStores

    init {
        repo = StoreRepo().getInstance()
        repo.loadStores(_allStores)
    }
}