package com.rjol.proyectoandroid.NewRegistersFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.R

class NewBookFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var releaseDateEditText: EditText
    private lateinit var imageUrlEditText: EditText
    private lateinit var sagaSpinner: Spinner
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = view.findViewById(R.id.new_book_name)
        priceEditText = view.findViewById(R.id.new_book_price)
        releaseDateEditText = view.findViewById(R.id.new_book_release_date)
        imageUrlEditText = view.findViewById(R.id.new_book_image_url)
        sagaSpinner = view.findViewById(R.id.new_book_saga)
        saveButton = view.findViewById(R.id.add_book_button)

        val sagaNames = mutableListOf<String>()
        val sagaIds = mutableListOf<String>()
        val db = FirebaseFirestore.getInstance()
        db.collection("sagas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val sagaName = document.get("nombre") as String
                    sagaNames.add(sagaName)
                    val sagaId = document.id
                    sagaIds.add(sagaId)
                }
                val sagaSpinnerAdapter = ArrayAdapter(requireContext(), com.bumptech.glide.R.layout.support_simple_spinner_dropdown_item, sagaNames)
                sagaSpinner.adapter = sagaSpinnerAdapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error al recuperar sagas", Toast.LENGTH_SHORT).show()
            }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val price = priceEditText.text.toString().trim()
            val releaseDate = releaseDateEditText.text.toString().trim()
            val imageUrl = imageUrlEditText.text.toString().trim()
            val sagaId = sagaIds[sagaSpinner.selectedItemPosition]

            if (name.isEmpty() || price.isEmpty() || releaseDate.isEmpty() || imageUrl.isEmpty()) {
                Toast.makeText(activity, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                val book = hashMapOf(
                    "nombre" to name,
                    "precio" to price,
                    "fechaLanzamiento" to releaseDate,
                    "imagen" to imageUrl,
                    "idSaga" to sagaId,
                )

                db.collection("libros")
                    .add(book)
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Libro agregado con éxito.", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "Error al agregar libro.", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}

