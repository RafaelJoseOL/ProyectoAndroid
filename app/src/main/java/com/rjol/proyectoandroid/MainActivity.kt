package com.rjol.proyectoandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()

        // Agregar el fragmento a la actividad
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, mainFragment)
            .commit()
    }
}
