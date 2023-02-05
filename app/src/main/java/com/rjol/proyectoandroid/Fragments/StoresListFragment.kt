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
import com.rjol.proyectoandroid.Adapters.StoresAdapter
import com.rjol.proyectoandroid.Model.StoreViewModel
import com.rjol.proyectoandroid.R

class StoresListFragment : Fragment() {

    private lateinit var adapter : StoresAdapter
    private lateinit var storeRecyclerView : RecyclerView
    private lateinit var viewModel : StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_stores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeRecyclerView = view.findViewById(R.id.store_recycler_view)
        storeRecyclerView.layoutManager = LinearLayoutManager(context)
        storeRecyclerView.setHasFixedSize(true)
        adapter = StoresAdapter()
        storeRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[StoreViewModel::class.java]

        viewModel.allStores.observe(viewLifecycleOwner, Observer {
            adapter.updateStoresList(it)
        })
    }
}