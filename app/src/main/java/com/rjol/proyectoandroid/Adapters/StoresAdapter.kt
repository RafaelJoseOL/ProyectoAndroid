package com.rjol.proyectoandroid.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.Model.Store
import com.rjol.proyectoandroid.R

class StoresAdapter : RecyclerView.Adapter<StoresAdapter.StoresHolder>() {

    private val storesList = ArrayList<Store>()
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_store, parent, false)
        return StoresHolder(itemView)
    }

    override fun onBindViewHolder(holder: StoresHolder, position: Int) {

        val currItem = storesList[position]
        holder.name.text = currItem.nombre
        Glide.with(holder.itemView.context)
            .load(currItem.imagen)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(currItem)
        }
    }

    override fun getItemCount(): Int {

        return storesList.size
    }

    fun updateStoresList(storesList : List<Store>) {
        this.storesList.clear()
        this.storesList.addAll(storesList)
        notifyDataSetChanged()
    }

    class StoresHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        val name : TextView = itemView.findViewById(R.id.store_name)
        val image : ImageView = itemView.findViewById(R.id.store_photo)
    }

    interface OnItemClickListener {
        fun onItemClick(store: Store)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}