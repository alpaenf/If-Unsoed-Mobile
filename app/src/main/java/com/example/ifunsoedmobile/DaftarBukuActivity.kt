package com.example.ifunsoedmobile

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifunsoedmobile.data.model.BookDoc
import com.example.ifunsoedmobile.databinding.ActivityDaftarBukuBinding
import com.example.ifunsoedmobile.ui.adapter.BookAdapter
import com.example.ifunsoedmobile.ui.adapter.OnBookClickListener // ✅ Ganti ke interface baru
import com.example.ifunsoedmobile.ui.fragment.BookDetailFragment
import com.example.ifunsoedmobile.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener { // ✅ Ganti implementasi interface

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val TAG = "DaftarBukuActivity"

    // ✅ Konstruktor adapter sekarang menerima OnBookClickListener
    private val adapter = BookAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Dibuat (setelah splash screen)")

        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ContentView di-set")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        Log.d(TAG, "onCreate: RecyclerView di-setup")

        viewModel.books.observe(this) { books ->
            Log.d(TAG, "viewModel.books.observe: Menerima data buku, jumlah: ${books?.size ?: "null"}")
            adapter.submitList(books)
        }

        Log.d(TAG, "onCreate: Memanggil viewModel.fetchBooks(\"kotlin programming\")")
        viewModel.fetchBooks("kotlin programming")
    }

    // ✅ Fungsi callback dari OnBookClickListener
    override fun onBookClick(book: BookDoc) {
        Log.d(TAG, "onBookClick: Item diklik - ${book.title}")

        // Tampilkan fragment detail buku
        val bookDetailFragment = BookDetailFragment.newInstance(
            title = book.title ?: "Judul Tidak Tersedia",
            author = book.authorName?.joinToString(", ") ?: "Penulis Tidak Diketahui",
            year = book.firstPublishYear?.toString() ?: "Tahun Tidak Diketahui",
            coverId = book.coverI ?: 0
        )
        bookDetailFragment.show(supportFragmentManager, "BookDetailFragmentTag")
    }

}
