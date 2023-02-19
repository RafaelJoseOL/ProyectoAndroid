package com.rjol.proyectoandroid.NewRegistersFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.rjol.proyectoandroid.R

class NewWriterFragment : Fragment() {

    private lateinit var nombreEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var editorialEditText: EditText
    private lateinit var webEditText: EditText
    private lateinit var imagenUrlEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_writer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nombreEditText = view.findViewById(R.id.new_writer_name)
        apellidoEditText = view.findViewById(R.id.new_writer_lastname)
        editorialEditText = view.findViewById(R.id.new_writer_publisher)
        webEditText = view.findViewById(R.id.new_writer_website)
        imagenUrlEditText = view.findViewById(R.id.new_writer_image_url)
        saveButton = view.findViewById(R.id.add_writer_button)

        val db = FirebaseFirestore.getInstance()
        val writerCollectionRef = db.collection("escritores")

        saveButton.setOnClickListener {
            val nombre = nombreEditText.text.toString().trim()
            val apellido = apellidoEditText.text.toString().trim()
            val editorial = editorialEditText.text.toString().trim()
            val web = webEditText.text.toString().trim()
            val imagenUrl = imagenUrlEditText.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || editorial.isEmpty() || web.isEmpty() || imagenUrl.isEmpty()) {
                Toast.makeText(activity, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                val writer = hashMapOf(
                    "nombre" to nombre,
                    "apellido" to apellido,
                    "editorial" to editorial,
                    "paginaWeb" to web,
                    "imagen" to imagenUrl
                )

                writerCollectionRef
                    .add(writer)
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Escritor agregado con éxito.", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.popBackStack()
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "Error al agregar escritor.", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
