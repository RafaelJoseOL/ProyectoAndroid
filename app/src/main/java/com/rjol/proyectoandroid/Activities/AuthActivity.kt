package com.rjol.proyectoandroid.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.rjol.proyectoandroid.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Código para añadir a la BBDD
//        val db = FirebaseFirestore.getInstance()
//        val escritor = hashMapOf(
//            "nombre" to "nombre",
//            "apellido" to "apellido",
//            "editorial" to "editorial"
//        )
//
//        db.collection("escritores").document("5").set(escritor)
//            .addOnSuccessListener {
//                Log.d(TAG, "DocumentSnapshot successfully written!")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error writing document", e)
//            }

        setup()
    }

    private fun setup() {
        title = "Conexión"

        val registerButton = findViewById<Button>(R.id.signUpButtonId)

        registerButton.setOnClickListener {
            val emailField = findViewById<EditText>(R.id.emailEditFieldId)
            val passwordField = findViewById<EditText>(R.id.passwordFieldId)
            if (emailField.text.isNotEmpty() && passwordField.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailField.text.toString(), passwordField.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, EmptyActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        registerAlert(true)
                    }
                }
            }
        }

        val logButton = findViewById<Button>(R.id.logInButtonId)

        logButton.setOnClickListener {
            val emailField = findViewById<EditText>(R.id.emailEditFieldId)
            val passwordField = findViewById<EditText>(R.id.passwordFieldId)
            if (emailField.text.isNotEmpty() && passwordField.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailField.text.toString(), passwordField.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, EmptyActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        registerAlert(false)
                    }
                }
            }
        }
    }

    private fun registerAlert(register: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        if(register){
            builder.setMessage("Error al crear usuario")
        } else {
            builder.setMessage("Error con las credenciales")
        }
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
