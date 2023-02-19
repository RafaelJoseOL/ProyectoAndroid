package com.rjol.proyectoandroid.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjol.proyectoandroid.Model.Book
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.R

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BooksHolder>() {

    private val booksList = ArrayList<Book>()
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_book, parent, false)
        return BooksHolder(itemView)
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {

        val currItem = booksList[position]
        holder.name.text = currItem.nombre
        Glide.with(holder.itemView.context)
            .load(currItem.imagen)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(currItem)
        }
    }

    override fun getItemCount(): Int {

        return booksList.size
    }

    fun updateBooksList(booksList : List<Book>) {
        this.booksList.clear()
        this.booksList.addAll(booksList)
        notifyDataSetChanged()
    }

    class BooksHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        val name : TextView = itemView.findViewById(R.id.book_name)
        val image : ImageView = itemView.findViewById(R.id.book_photo)
    }

    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}