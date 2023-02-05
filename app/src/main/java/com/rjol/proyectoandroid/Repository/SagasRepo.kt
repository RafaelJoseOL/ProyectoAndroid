package com.rjol.proyectoandroid.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Model.Saga

class SagasRepo {

    private val db = FirebaseFirestore.getInstance().collection("sagas")

    @Volatile
    private var INSTANCE: SagasRepo? = null

    fun getInstance(): SagasRepo {
        return INSTANCE ?: synchronized(this) {

            val instance = SagasRepo()
            INSTANCE = instance
            instance
        }
    }

    fun loadSagas(sagasList: MutableLiveData<List<Saga>>) {
        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("error", firebaseFirestoreException.toString())
                return@addSnapshotListener
            }
            val _sagasList = mutableListOf<Saga>()
            db
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val saga = document.toObject(Saga::class.java)
                        _sagasList.add(saga)
                    }
                    sagasList.postValue(_sagasList)
                }
                .addOnFailureListener { exception ->

                }

        }
    }
}