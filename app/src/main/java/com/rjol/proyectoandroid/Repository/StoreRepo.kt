package com.rjol.proyectoandroid.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Model.Store

class StoreRepo {

    private val db = FirebaseFirestore.getInstance().collection("tiendas")

    @Volatile
    private var INSTANCE: StoreRepo? = null

    fun getInstance(): StoreRepo {
        return INSTANCE ?: synchronized(this) {

            val instance = StoreRepo()
            INSTANCE = instance
            instance
        }
    }

    fun loadStores(storesList: MutableLiveData<List<Store>>) {
        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("error", firebaseFirestoreException.toString())
                return@addSnapshotListener
            }
            val _storesList = mutableListOf<Store>()
            db
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val store = document.toObject(Store::class.java)
                        _storesList.add(store)
                    }
                    storesList.postValue(_storesList)
                }
                .addOnFailureListener { exception ->

                }

        }
    }
}