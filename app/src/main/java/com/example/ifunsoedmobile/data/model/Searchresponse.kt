package com.example.ifunsoedmobile.data.model

import com.google.gson.annotations.SerializedName // Import yang dibutuhkan

// Ini adalah data class utama untuk respons pencarian
data class SearchResponse(
    @SerializedName("docs")
    val docs: List<BookDoc>
)

// Ini adalah data class untuk setiap item buku dalam 'docs'
data class BookDoc(
    @SerializedName("key") // Ditambahkan untuk field 'key' dari JSON
    val key: String?,    // Dipindahkan ke konstruktor utama dan tipe diubah

    @SerializedName("title")
    val title: String?,

    @SerializedName("author_name")
    val authorName: List<String>?,

    @SerializedName("first_publish_year")
    val firstPublishYear: Int?
) // Properti 'key' yang di body kelas dihapus
