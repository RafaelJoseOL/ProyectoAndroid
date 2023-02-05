package com.rjol.proyectoandroid.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.R

class SagasAdapter : RecyclerView.Adapter<SagasAdapter.SagasHolder>() {

    private val sagasList = ArrayList<Saga>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SagasHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_saga, parent, false)
        return SagasHolder(itemView)
    }

    override fun onBindViewHolder(holder: SagasHolder, position: Int) {

        val currItem = sagasList[position]
        holder.name.text = currItem.nombre
    }

    override fun getItemCount(): Int {

        return sagasList.size
    }

    fun updateSagasList(sagasList : List<Saga>) {
        this.sagasList.clear()
        this.sagasList.addAll(sagasList)
        notifyDataSetChanged()
    }

    class SagasHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        val name : TextView = itemView.findViewById(R.id.saga_name)
    }
}