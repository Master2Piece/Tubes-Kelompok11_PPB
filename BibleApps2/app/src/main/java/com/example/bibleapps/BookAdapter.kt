package com.example.bibleapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val bookList: List<Pair<String, String>>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val englishTextView: TextView = itemView.findViewById(R.id.textViewEnglish)
        val indonesianTextView: TextView = itemView.findViewById(R.id.textViewIndonesian)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.englishTextView.text = book.first
        holder.indonesianTextView.text = book.second
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}
