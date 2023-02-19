package com.rjol.proyectoandroid.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rjol.proyectoandroid.Adapters.SagasAdapter
import com.rjol.proyectoandroid.Adapters.WritersAdapter
import com.rjol.proyectoandroid.DetailsFragments.SagasDetailsFragment
import com.rjol.proyectoandroid.DetailsFragments.WriterDetailsFragment
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.Model.SagasViewModel
import com.rjol.proyectoandroid.Model.Writer
import com.rjol.proyectoandroid.NewRegistersFragments.NewSagaFragment
import com.rjol.proyectoandroid.NewRegistersFragments.NewWriterFragment
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

        adapter.setOnItemClickListener(object : SagasAdapter.OnItemClickListener {
            override fun onItemClick(saga: Saga) {
                val detailsFragment = SagasDetailsFragment()
                val bundle = Bundle()
                bundle.putString("name", saga.nombre)
                bundle.putString("genre", saga.genero)
                bundle.putString("booksNumber", saga.numeroLibros)
                bundle.putString("idSaga", saga.idSaga)
                bundle.putString("idWriter", saga.idEscritor)
                detailsFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_main, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        val newSagasButton = view.findViewById<Button>(R.id.new_saga_button)
        newSagasButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_main, NewSagaFragment())
                .addToBackStack(null)
                .commit()
        }

        viewModel = ViewModelProvider(this)[SagasViewModel::class.java]

        viewModel.allSagas.observe(viewLifecycleOwner, Observer {
            adapter.updateSagasList(it)
        })
    }
}