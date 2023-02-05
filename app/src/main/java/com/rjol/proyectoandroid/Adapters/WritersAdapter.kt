package com.rjol.proyectoandroid.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjol.proyectoandroid.Model.Writer
import com.rjol.proyectoandroid.R

class WritersAdapter : RecyclerView.Adapter<WritersAdapter.WriterHolder>() {

    private val writersList = ArrayList<Writer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriterHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_writer, parent, false)
        return WriterHolder(itemView)
    }

    override fun onBindViewHolder(holder: WriterHolder, position: Int) {

        val currItem = writersList[position]
        holder.name.text = currItem.nombre
        holder.lastName.text = currItem.apellido
        Glide.with(holder.itemView.context)
            .load(currItem.imagen)
            .into(holder.image)
    }

    override fun getItemCount(): Int {

        return writersList.size
    }

    fun updateWritersList(writersList : List<Writer>) {
        this.writersList.clear()
        this.writersList.addAll(writersList)
        notifyDataSetChanged()
    }

    class WriterHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        val name : TextView = itemView.findViewById(R.id.book_name)
        val lastName : TextView = itemView.findViewById(R.id.writer_last_name)
        val image : ImageView = itemView.findViewById(R.id.writer_photo)
    }
}