package com.rjol.proyectoandroid.DetailsFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Adapters.BooksAdapter
import com.rjol.proyectoandroid.Adapters.StoresAdapter
import com.rjol.proyectoandroid.Model.Book
import com.rjol.proyectoandroid.Model.Store
import com.rjol.proyectoandroid.R

class BooksDetailsFragment : Fragment() {

    private lateinit var adapter: StoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val releaseDate = arguments?.getString("releaseDate")
        val price = arguments?.getString("price")
        val bookImageUrl = arguments?.getString("bookImageUrl")
        val idSaga = arguments?.getString("idSaga") ?:""
        val idBook = arguments?.getString("idBook")

        val bookNameTextView = view.findViewById<TextView>(R.id.book_details_name)
        bookNameTextView.text = name

        val bookReleaseDate = view.findViewById<TextView>(R.id.book_details_release_date)
        bookReleaseDate.text = buildString {
            append("Lanzamiento: ")
            append(releaseDate)
        }

        val bookPrice = view.findViewById<TextView>(R.id.book_details_price)
        bookPrice.text = buildString {
            append("PVP: ")
            append(price)
            append("€")
        }

        val imageView = view.findViewById<ImageView>(R.id.book_details_photo)

        Glide.with(this)
            .load(bookImageUrl)
            .into(imageView)

        val storesRecyclerView = view.findViewById<RecyclerView>(R.id.book_stores_rv)
        storesRecyclerView.layoutManager = LinearLayoutManager(context)
        storesRecyclerView.setHasFixedSize(true)
        adapter = StoresAdapter()
        storesRecyclerView.adapter = adapter

        val db = FirebaseFirestore.getInstance()

        val bookSagaTextView = view.findViewById<TextView>(R.id.book_saga)
        val sagaRef = db.collection("sagas")
        val querySagas = sagaRef.document(idSaga)
        querySagas.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val bookSaga = document.getString("nombre")
                    bookSagaTextView.text = buildString {
                        append("Saga: ")
                        append(bookSaga)
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        val storesRef = db.collection("tiendas")

        storesRef.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener
            }

            val storesList = mutableListOf<Store>()
            for (doc in value!!) {
                val store = doc.toObject(Store::class.java)
                storesList.add(store)
            }
            adapter.updateStoresList(storesList)
        }

        adapter.setOnItemClickListener(object : StoresAdapter.OnItemClickListener {
            override fun onItemClick(store: Store) {
                val intent = Intent(Intent.ACTION_VIEW)
                if(store.nombre == "Casa del Libro"){
                    intent.data = Uri.parse(store.url + "?q=" + name)
                } else if(store.nombre == "Amazon"){
                intent.data = Uri.parse(store.url + "s?k=" + name)
                } else if(store.nombre == "Fnac") {
                    intent.data = Uri.parse(store.url + "SearchResult/ResultList.aspx?SDM=mosaic&Search=" + name)
                } else {
                    intent.data = Uri.parse("www.google.es/search?q=" + name)
                }
                startActivity(intent)
            }
        })

        val deleteButton = view.findViewById<Button>(R.id.button_delete_book)
        deleteButton.setOnClickListener {
            db.collection("libros")
                .document(idBook!!)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(activity, "Libro eliminado con éxito", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                }
                .addOnFailureListener { e ->
                    Log.w(WriterDetailsFragment.TAG, "Error deleting document", e)
                    Toast.makeText(activity, "Error al eliminar libro", Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        private const val TAG = "SagaDetailsFragment"
    }
}