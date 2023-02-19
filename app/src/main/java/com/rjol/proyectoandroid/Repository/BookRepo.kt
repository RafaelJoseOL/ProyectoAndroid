package com.rjol.proyectoandroid.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Model.Book

class BookRepo {

    private val db = FirebaseFirestore.getInstance().collection("libros")

    @Volatile
    private var INSTANCE: BookRepo? = null

    fun getInstance(): BookRepo {
        return INSTANCE ?: synchronized(this) {

            val instance = BookRepo()
            INSTANCE = instance
            instance
        }
    }

    fun loadBooks(booksList: MutableLiveData<List<Book>>) {
        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("error", firebaseFirestoreException.toString())
                return@addSnapshotListener
            }
            val _booksList = mutableListOf<Book>()
            db
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val book = document.toObject(Book::class.java)
                        book.idBook = document.id
                        _booksList.add(book)
                    }
                    booksList.postValue(_booksList)
                }
                .addOnFailureListener { exception ->

                }

        }
    }
}