package com.rjol.proyectoandroid.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rjol.proyectoandroid.Fragments.MainFragment
import com.rjol.proyectoandroid.R

class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MainFragment()
        fragmentTransaction.replace(R.id.fragment_main, fragment)
        fragmentTransaction.commit()
    }
}