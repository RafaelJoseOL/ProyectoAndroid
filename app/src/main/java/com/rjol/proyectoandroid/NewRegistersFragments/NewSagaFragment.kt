package com.rjol.proyectoandroid.NewRegistersFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.R

class NewSagaFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var genreEditText: EditText
    private lateinit var numBooksEditText: EditText
    private lateinit var writerSpinner: Spinner
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_saga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = view.findViewById(R.id.new_saga_name)
        genreEditText = view.findViewById(R.id.new_saga_genre)
        numBooksEditText = view.findViewById(R.id.new_saga_books_number)
        writerSpinner = view.findViewById(R.id.new_saga_writer)
        saveButton = view.findViewById(R.id.add_saga_button)

        val writerNames = mutableListOf<String>()
        val writerIds = mutableListOf<String>()
        val db = FirebaseFirestore.getInstance()
        db.collection("escritores")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val writerName = document.get("nombre") as String
                    val writerLastName = document.get("apellido") as String
                    writerNames.add(writerName + " " + writerLastName)
                    val writerId = document.id
                    writerIds.add(writerId)
                }
                val writerSpinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, writerNames)
                writerSpinner.adapter = writerSpinnerAdapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error al recuperar escritores", Toast.LENGTH_SHORT).show()
            }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val genre = genreEditText.text.toString().trim()
            val numBooks = numBooksEditText.text.toString().trim()
            val writerId = writerIds[writerSpinner.selectedItemPosition]

            if (name.isEmpty() || genre.isEmpty() || numBooks == null) {
                Toast.makeText(activity, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                val saga = hashMapOf(
                    "nombre" to name,
                    "genero" to genre,
                    "numeroLibros" to numBooks,
                    "idEscritor" to writerId,
                )

                db.collection("sagas")
                    .add(saga)
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Saga agregada con éxito.", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "Error al agregar saga.", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
