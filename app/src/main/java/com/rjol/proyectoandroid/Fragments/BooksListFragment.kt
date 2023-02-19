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
import com.rjol.proyectoandroid.Adapters.BooksAdapter
import com.rjol.proyectoandroid.Adapters.SagasAdapter
import com.rjol.proyectoandroid.DetailsFragments.BooksDetailsFragment
import com.rjol.proyectoandroid.DetailsFragments.SagasDetailsFragment
import com.rjol.proyectoandroid.Model.Book
import com.rjol.proyectoandroid.Model.BooksViewModel
import com.rjol.proyectoandroid.Model.Saga
import com.rjol.proyectoandroid.NewRegistersFragments.NewBookFragment
import com.rjol.proyectoandroid.R

class BooksListFragment : Fragment() {

    private lateinit var adapter : BooksAdapter
    private lateinit var sagasRecyclerView: RecyclerView
    private lateinit var viewModel : BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sagasRecyclerView = view.findViewById(R.id.books_recycler_view)
        sagasRecyclerView.layoutManager = LinearLayoutManager(context)
        sagasRecyclerView.setHasFixedSize(true)
        adapter = BooksAdapter()
        sagasRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : BooksAdapter.OnItemClickListener {
            override fun onItemClick(book: Book) {
                val detailsFragment = BooksDetailsFragment()
                val bundle = Bundle()
                bundle.putString("name", book.nombre)
                bundle.putString("releaseDate", book.fechaLanzamiento)
                bundle.putString("price", book.precio)
                bundle.putString("bookImageUrl", book.imagen)
                bundle.putString("idSaga", book.idSaga)
                bundle.putString("idBook", book.idBook)
                detailsFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_main, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        val newBookButton = view.findViewById<Button>(R.id.new_book_button)
        newBookButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_main, NewBookFragment())
                .addToBackStack(null)
                .commit()
        }

        viewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        viewModel.allBooks.observe(viewLifecycleOwner, Observer {
            adapter.updateBooksList(it)
        })
    }
}