package com.example.ifunsoedmobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ifunsoedmobile.data.model.BookDoc
import com.unsoed.informatikamobile.databinding.ListBukuBinding

// Menggunakan ListAdapter, bukan RecyclerView.Adapter lagi
class BookAdapter : ListAdapter<BookDoc, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ListBukuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position) // Mengambil item menggunakan getItem() dari ListAdapter
        holder.binding.tvTitle.text = book.title ?: "No Title"
        holder.binding.tvAuthor.text = book.authorName?.joinToString(", ") ?: "Unknown Author"
        holder.binding.tvYear.text = book.firstPublishYear?.toString() ?: "-"
    }

    // BookViewHolder tetap sama
    inner class BookViewHolder(val binding: ListBukuBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Kelas DiffUtil.ItemCallback untuk BookDoc
    // Ini membantu ListAdapter menentukan perubahan pada daftar secara efisien
    class BookDiffCallback : DiffUtil.ItemCallback<BookDoc>() {
        override fun areItemsTheSame(oldItem: BookDoc, newItem: BookDoc): Boolean {
            // Asumsikan 'key' adalah ID unik. Jika BookDoc Anda memiliki ID unik lain, gunakan itu.
            // Jika tidak ada ID unik, Anda bisa membandingkan beberapa field, misal title dan tahun terbit.
            // Untuk contoh ini, kita asumsikan 'key' ada dan unik atau 'title' cukup unik untuk identifikasi item.
            return oldItem.key == newItem.key // Ganti dengan ID unik Anda jika ada
        }

        override fun areContentsTheSame(oldItem: BookDoc, newItem: BookDoc): Boolean {
            // Membandingkan semua konten. Jika BookDoc adalah data class, ini sudah cukup.
            return oldItem == newItem
        }
    }

    // Metode setData() tidak lagi diperlukan seperti sebelumnya.
    // Pembaruan list akan dilakukan dengan memanggil adapter.submitList(newList)
    // dari Activity atau ViewModel.
    // Jika Anda ingin tetap memiliki fungsi helper di adapter:
    // fun updateBooks(newBooks: List<BookDoc>?) {
    //    submitList(newBooks)
    // }
}
