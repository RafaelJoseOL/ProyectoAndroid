package com.rjol.proyectoandroid.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjol.proyectoandroid.Repository.WriterRepo

class WriterViewModel : ViewModel() {

    private val repo : WriterRepo
    private val _allWriters = MutableLiveData<List<Writer>>()
    val allWriters : LiveData<List<Writer>> = _allWriters

    init {
        repo = WriterRepo().getInstance()
        repo.loadWriters(_allWriters)
    }
}