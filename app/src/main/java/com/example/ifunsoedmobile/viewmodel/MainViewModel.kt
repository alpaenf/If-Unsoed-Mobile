package com.example.ifunsoedmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifunsoedmobile.data.model.BookDoc
import com.example.ifunsoedmobile.data.network.RetrofitInstance // Pastikan ini benar
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookDoc>>()
    val books: LiveData<List<BookDoc>> = _books
    private val TAG = "MainViewModel" // Tag untuk Log

    fun fetchBooks(query: String) {
        Log.d(TAG, "fetchBooks: Dipanggil dengan query = '$query'")
        viewModelScope.launch {
            Log.d(TAG, "fetchBooks: Memulai coroutine viewModelScope")
            try {
                Log.i(TAG, "fetchBooks: Mencoba mengambil data dari API...")
                // Asumsikan RetrofitInstance.api.searchBooks adalah suspend function
                // dan menerima parameter 'limit'
                val response = RetrofitInstance.api.searchBooks(query, limit = 10) 
                
                Log.d(TAG, "fetchBooks: Response diterima. isSuccessful: ${response.isSuccessful}, Code: ${response.code()}, Message: ${response.message()}")

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    // Log.d(TAG, "fetchBooks: Response body (JSON mentah): ${responseBody?.toString()}") // Aktifkan jika perlu, hati-hati jika body besar

                    val result = responseBody?.docs ?: emptyList()
                    Log.i(TAG, "fetchBooks: Parsing berhasil. Jumlah buku: ${result.size}")

                    if (result.isNotEmpty()) {
                        // Log detail buku pertama untuk verifikasi parsing
                        val firstBook = result[0]
                        Log.d(TAG, "fetchBooks: Buku pertama - Key: ${firstBook.key}, Title: ${firstBook.title}, Authors: ${firstBook.authorName?.joinToString()}, Year: ${firstBook.firstPublishYear}")
                    } else {
                        Log.w(TAG, "fetchBooks: Daftar buku yang diparsing dari API kosong.")
                    }
                    
                    // Menggunakan postValue karena kita berada di dalam coroutine (bisa jadi background thread)
                    _books.postValue(result) 
                    Log.i(TAG, "fetchBooks: _books LiveData diperbarui dengan ${result.size} buku.")

                } else {
                    Log.e(TAG, "fetchBooks: Panggilan API GAGAL. Code: ${response.code()}, Message: ${response.message()}")
                    _books.postValue(emptyList()) // Set list kosong jika API call gagal
                }
            } catch (e: Exception) {
                Log.e(TAG, "fetchBooks: EXCEPTION saat mengambil/memproses data API!", e)
                _books.postValue(emptyList()) // Set list kosong jika terjadi exception
            }
        }
    }
}
