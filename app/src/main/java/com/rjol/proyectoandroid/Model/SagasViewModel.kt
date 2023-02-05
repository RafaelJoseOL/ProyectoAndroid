package com.rjol.proyectoandroid.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjol.proyectoandroid.Repository.SagasRepo

class SagasViewModel : ViewModel() {

    private val repo : SagasRepo
    private val _allSagas = MutableLiveData<List<Saga>>()
    val allSagas : LiveData<List<Saga>> = _allSagas

    init {
        repo = SagasRepo().getInstance()
        repo.loadSagas(_allSagas)
    }
}