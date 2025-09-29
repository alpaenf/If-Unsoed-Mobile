package com.example.ifunsoedmobile.data.network

import com.example.ifunsoedmobile.data.model.SearchResponse // Ditambahkan
import retrofit2.Response // Ditambahkan
import retrofit2.http.GET // Ditambahkan
import retrofit2.http.Query // Ditambahkan

interface OpenLibraryApi {

    @GET(value = "search.json")
    suspend fun searchBooks(
        @Query(value = "q") query: String,
        @Query(value = "limit") limit: Int
    ): Response<SearchResponse>
}
