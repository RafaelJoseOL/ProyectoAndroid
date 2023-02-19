package com.rjol.proyectoandroid.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Model.Writer

class WriterRepo {

    private val db = FirebaseFirestore.getInstance().collection("escritores")

    @Volatile
    private var INSTANCE: WriterRepo? = null

    fun getInstance(): WriterRepo {
        return INSTANCE ?: synchronized(this) {

            val instance = WriterRepo()
            INSTANCE = instance
            instance
        }
    }

    fun loadWriters(writersList: MutableLiveData<List<Writer>>) {
        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("error", firebaseFirestoreException.toString())
                return@addSnapshotListener
            }
            val _writersList = mutableListOf<Writer>()
            db
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val writer = document.toObject(Writer::class.java)
                        writer.idEscritor = document.id
                        _writersList.add(writer)
                    }
                    writersList.postValue(_writersList)
                }
                .addOnFailureListener { exception ->

                }

        }
    }
}