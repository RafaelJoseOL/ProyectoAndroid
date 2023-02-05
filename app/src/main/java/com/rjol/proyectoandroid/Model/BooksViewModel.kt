package com.rjol.proyectoandroid.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjol.proyectoandroid.Repository.BookRepo

class BooksViewModel : ViewModel() {

    private val repo : BookRepo
    private val _allBooks = MutableLiveData<List<Book>>()
    val allBooks : LiveData<List<Book>> = _allBooks

    init {
        repo = BookRepo().getInstance()
        repo.loadBooks(_allBooks)
    }
}