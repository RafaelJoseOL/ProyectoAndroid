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
import com.rjol.proyectoandroid.Adapters.SagasAdapter
import com.rjol.proyectoandroid.Model.SagasViewModel
import com.rjol.proyectoandroid.R

class SagasListFragment : Fragment() {

    private lateinit var adapter : SagasAdapter
    private lateinit var sagasRecyclerView: RecyclerView
    private lateinit var viewModel : SagasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_sagas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sagasRecyclerView = view.findViewById(R.id.sagas_recycler_view)
        sagasRecyclerView.layoutManager = LinearLayoutManager(context)
        sagasRecyclerView.setHasFixedSize(true)
        adapter = SagasAdapter()
        sagasRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[SagasViewModel::class.java]

        viewModel.allSagas.observe(viewLifecycleOwner, Observer {
            adapter.updateSagasList(it)
        })
    }
}