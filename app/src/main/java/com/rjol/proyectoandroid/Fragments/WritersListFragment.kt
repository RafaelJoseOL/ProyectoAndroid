package com.rjol.proyectoandroid.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rjol.proyectoandroid.Adapters.WritersAdapter
import com.rjol.proyectoandroid.Model.WriterViewModel
import com.rjol.proyectoandroid.R

class WritersListFragment : Fragment() {

    private lateinit var adapter : WritersAdapter
    private lateinit var writersRecyclerView: RecyclerView
    private lateinit var viewModel : WriterViewModel

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

        viewModel = ViewModelProvider(this).get(WriterViewModel::class.java)

        viewModel.allWriters.observe(viewLifecycleOwner, Observer {
            adapter.updateWritersList(it)
        })
    }
}