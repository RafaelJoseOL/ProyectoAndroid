package com.rjol.proyectoandroid.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rjol.proyectoandroid.Adapters.WritersAdapter
import com.rjol.proyectoandroid.DetailsFragments.WriterDetailsFragment
import com.rjol.proyectoandroid.Model.Writer
import com.rjol.proyectoandroid.Model.WriterViewModel
import com.rjol.proyectoandroid.NewRegistersFragments.NewWriterFragment
import com.rjol.proyectoandroid.R

class WritersListFragment : Fragment() {

    private lateinit var adapter : WritersAdapter
    private lateinit var writersRecyclerView: RecyclerView
    private lateinit var viewModel : WriterViewModel
    private lateinit var writerSearchBar: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_writers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        writersRecyclerView = view.findViewById(R.id.writers_recycler_view)
        writersRecyclerView.layoutManager = LinearLayoutManager(context)
        writersRecyclerView.setHasFixedSize(true)
        adapter = WritersAdapter()
        writersRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : WritersAdapter.OnItemClickListener {
            override fun onItemClick(writer: Writer) {
                val detailsFragment = WriterDetailsFragment()
                val bundle = Bundle()
                bundle.putString("name", writer.nombre + " " + writer.apellido)
                bundle.putString("website", writer.paginaWeb)
                bundle.putString("publisher", writer.editorial)
                bundle.putString("image", writer.imagen)
                bundle.putString("idEscritor", writer.idEscritor)
                detailsFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_main, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        val newWriterButton = view.findViewById<Button>(R.id.new_writer_button)
        newWriterButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_main, NewWriterFragment())
                .addToBackStack(null)
                .commit()
        }

        viewModel = ViewModelProvider(this).get(WriterViewModel::class.java)

        viewModel.allWriters.observe(viewLifecycleOwner, Observer {
            adapter.updateWritersList(it)
        })
    }
}