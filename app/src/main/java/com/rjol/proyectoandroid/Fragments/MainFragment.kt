package com.rjol.proyectoandroid.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rjol.proyectoandroid.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Tablas"

        val writersListButton = view.findViewById<ImageButton>(R.id.button_writers)
        writersListButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val fragment = WritersListFragment()
            fragmentTransaction.replace(R.id.fragment_main, fragment)
            fragmentTransaction.addToBackStack(null)
            (activity as AppCompatActivity).supportActionBar?.title = "Escritores"
            fragmentTransaction.commit()
        }

        val sagasListButton = view.findViewById<ImageButton>(R.id.button_sagas)
        sagasListButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val fragment = SagasListFragment()
            fragmentTransaction.replace(R.id.fragment_main, fragment)
            fragmentTransaction.addToBackStack(null)
            (activity as AppCompatActivity).supportActionBar?.title = "Sagas"
            fragmentTransaction.commit()
        }

        val booksListButton = view.findViewById<ImageButton>(R.id.button_books)
        booksListButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val fragment = BooksListFragment()
            fragmentTransaction.replace(R.id.fragment_main, fragment)
            fragmentTransaction.addToBackStack(null)
            (activity as AppCompatActivity).supportActionBar?.title = "Libros"
            fragmentTransaction.commit()
        }

        val storesListButton = view.findViewById<ImageButton>(R.id.button_stores)
        storesListButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val fragment = StoresListFragment()
            fragmentTransaction.replace(R.id.fragment_main, fragment)
            fragmentTransaction.addToBackStack(null)
            (activity as AppCompatActivity).supportActionBar?.title = "Tiendas"
            fragmentTransaction.commit()
        }

        val logOutButton = view.findViewById<Button>(R.id.logOutButton)
        logOutButton.setOnClickListener(){
            val activity = requireActivity()
            activity.finish()
        }

        return view
    }
}