package com.example.ifunsoedmobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ifunsoedmobile.data.model.BookDoc
import com.example.ifunsoedmobile.databinding.ListBukuBinding

// Mengganti nama interface agar sesuai dengan Activity
interface OnBookClickListener {
    fun onBookClick(book: BookDoc)
}

class BookAdapter(
    // Mengganti nama parameter constructor
    private val onBookClickListener: OnBookClickListener
) : ListAdapter<BookDoc, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ListBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.binding.tvTitle.text = book.title ?: "No Title"
        holder.binding.tvAuthor.text = book.authorName?.joinToString(", ") ?: "Unknown Author"
        holder.binding.tvYear.text = book.firstPublishYear?.toString() ?: "-"

        holder.binding.root.setOnClickListener {
            // Memanggil metode dari interface yang sudah diganti namanya
            onBookClickListener.onBookClick(book)
        }
    }

    inner class BookViewHolder(val binding: ListBukuBinding) :
        RecyclerView.ViewHolder(binding.root)

    class BookDiffCallback : DiffUtil.ItemCallback<BookDoc>() {
        override fun areItemsTheSame(oldItem: BookDoc, newItem: BookDoc): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: BookDoc, newItem: BookDoc): Boolean {
            return oldItem == newItem
        }
    }
}
