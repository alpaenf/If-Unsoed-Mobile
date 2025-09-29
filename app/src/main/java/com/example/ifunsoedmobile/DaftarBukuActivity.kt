package com.example.ifunsoedmobile

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen // Import untuk SplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.informatikamobile.databinding.ActivityDaftarBukuBinding
import com.example.ifunsoedmobile.ui.adapter.BookAdapter
import com.example.ifunsoedmobile.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter()
    private val TAG = "DaftarBukuActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        // Panggil installSplashScreen() SEBELUM super.onCreate dan setContentView
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Dibuat (setelah splash screen)")

        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ContentView di-set")


        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        Log.d(TAG, "onCreate: RecyclerView di-setup")

        // Observe LiveData dari ViewModel
        viewModel.books.observe(this) { books ->
            Log.d(TAG, "viewModel.books.observe: Menerima data buku, jumlah: ${books?.size ?: "null"}")
            if (books.isNullOrEmpty()) {
                Log.w(TAG, "viewModel.books.observe: Daftar buku kosong atau null")
            } else {
                Log.i(TAG, "viewModel.books.observe: Mengirim ${books.size} buku ke adapter.")
            }
            adapter.submitList(books)
        }

        // Panggil data awal
        Log.d(TAG, "onCreate: Memanggil viewModel.fetchBooks(\"kotlin programming\")")
        viewModel.fetchBooks("kotlin programming")
    }
}
