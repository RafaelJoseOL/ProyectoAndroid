package com.rjol.proyectoandroid.DetailsFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Adapters.BooksAdapter
import com.rjol.proyectoandroid.Model.Book
import com.rjol.proyectoandroid.R

class SagasDetailsFragment : Fragment() {

    private lateinit var adapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_saga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val genre = arguments?.getString("genre")
        val booksNumber = arguments?.getString("booksNumber")
        val idSaga = arguments?.getString("idSaga")
        val idWriter = arguments?.getString("idWriter") ?:""

        val sagaNameTextView = view.findViewById<TextView>(R.id.saga_details_name)
        sagaNameTextView.text = name

        val sagaGenreTextView = view.findViewById<TextView>(R.id.saga_details_genre)
        sagaGenreTextView.text = genre

        val sagaBooksNumber = view.findViewById<TextView>(R.id.saga_details_books_number)
        sagaBooksNumber.text = buildString {
        append("Número de libros: ")
        append(booksNumber)
        }

        val booksRecyclerView = view.findViewById<RecyclerView>(R.id.saga_books_rv)
        booksRecyclerView.layoutManager = LinearLayoutManager(context)
        booksRecyclerView.setHasFixedSize(true)
        adapter = BooksAdapter()
        booksRecyclerView.adapter = adapter

        val db = FirebaseFirestore.getInstance()

        val sagaWriterTextView = view.findViewById<TextView>(R.id.saga_writer)
        val writersRef = db.collection("escritores")
        val queryWriters = writersRef.document(idWriter)
        queryWriters.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val writerName = document.getString("nombre") + " " + document.getString("apellido")
                    sagaWriterTextView.text = writerName
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        val sagasRef = db.collection("libros")
        val querySagas = sagasRef.whereEqualTo("idSaga", idSaga)

        querySagas.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener
            }

            val booksList = mutableListOf<Book>()
            for (doc in value!!) {
                val book = doc.toObject(Book::class.java)
                booksList.add(book)
            }
            adapter.updateBooksList(booksList)
        }

        val deleteButton = view.findViewById<Button>(R.id.button_delete_saga)
        deleteButton.setOnClickListener {
            db.collection("sagas")
                .document(idSaga!!)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(activity, "Saga eliminada con éxito", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                }
                .addOnFailureListener { e ->
                    Log.w(WriterDetailsFragment.TAG, "Error deleting document", e)
                    Toast.makeText(activity, "Error al eliminar saga", Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        private const val TAG = "SagaDetailsFragment"
    }
}