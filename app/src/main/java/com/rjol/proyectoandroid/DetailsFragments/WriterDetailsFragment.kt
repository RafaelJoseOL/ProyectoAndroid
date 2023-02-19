package com.rjol.proyectoandroid.DetailsFragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.Adapters.BooksAdapter
import com.rjol.proyectoandroid.Adapters.SagasAdapter
import com.rjol.proyectoandroid.Model.Book
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.R

class WriterDetailsFragment : Fragment() {

    private lateinit var adapter: SagasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_writer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val website = arguments?.getString("website")
        val publisher = arguments?.getString("publisher")
        val imageUrl = arguments?.getString("image")
        val writerId = arguments?.getString("idEscritor")

        val writerNameTextView = view.findViewById<TextView>(R.id.writer_name)
        writerNameTextView.text = name

        val writerPublisher = view.findViewById<TextView>(R.id.writer_publisher)
        writerPublisher.text = buildString {
            append("Editorial: ")
            append(publisher)
        }

        val writerWebsite = view.findViewById<TextView>(R.id.writer_website)
        writerWebsite.text = website
        writerWebsite.autoLinkMask = Linkify.WEB_URLS
        writerWebsite.movementMethod = LinkMovementMethod.getInstance()

        val imageView = view.findViewById<ImageView>(R.id.writer_photo)

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        val sagasRecyclerView = view.findViewById<RecyclerView>(R.id.writer_sagas_rv)
        sagasRecyclerView.layoutManager = LinearLayoutManager(context)
        sagasRecyclerView.setHasFixedSize(true)
        adapter = SagasAdapter()
        sagasRecyclerView.adapter = adapter

        val db = FirebaseFirestore.getInstance()
        val sagasRef = db.collection("sagas")
        val query = sagasRef.whereEqualTo("idEscritor", writerId)

        query.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener
            }

            val sagasList = mutableListOf<Saga>()
            for (doc in value!!) {
                val saga = doc.toObject(Saga::class.java)
                sagasList.add(saga)
            }
            adapter.updateSagasList(sagasList)
        }

        val deleteButton = view.findViewById<Button>(R.id.button_delete_writer)
        deleteButton.setOnClickListener {
            db.collection("escritores")
                .document(writerId!!)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(activity, "Escritor eliminado con éxito", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error deleting document", e)
                    Toast.makeText(activity, "Error al eliminar escritor", Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        const val TAG = "WriterDetailsFragment"
    }
}
