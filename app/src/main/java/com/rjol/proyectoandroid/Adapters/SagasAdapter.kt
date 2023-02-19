package com.rjol.proyectoandroid.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.rjol.proyectoandroid.DetailsFragments.SagasDetailsFragment
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.Model.Writer
import com.rjol.proyectoandroid.R

class SagasAdapter : RecyclerView.Adapter<SagasAdapter.SagasHolder>() {

    private val sagasList = ArrayList<Saga>()
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SagasHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_saga, parent, false)
        return SagasHolder(itemView)
    }

    override fun onBindViewHolder(holder: SagasHolder, position: Int) {

        val currItem = sagasList[position]
        holder.name.text = currItem.nombre
        holder.itemView.setOnClickListener {
            listener?.onItemClick(currItem)
        }
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

    interface OnItemClickListener {
        fun onItemClick(saga: Saga)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}