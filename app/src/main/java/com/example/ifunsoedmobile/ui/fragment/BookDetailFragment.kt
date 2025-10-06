package com.example.ifunsoedmobile.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ifunsoedmobile.R // Import R class
import com.example.ifunsoedmobile.databinding.FragmentBookDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BookDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    private var title: String? = null
    private var author: String? = null
    private var year: String? = null
    private var coverId: Int = 0

    // Tema Material 3 BottomSheet
    override fun getTheme(): Int = com.google.android.material.R.style.ThemeOverlay_Material3_BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ambil data dari arguments
        arguments?.let {
            title = it.getString(ARG_TITLE)
            author = it.getString(ARG_AUTHOR)
            year = it.getString(ARG_YEAR)
            coverId = it.getInt(ARG_COVER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        binding.textViewTitle.text = title ?: "-"
        binding.textViewAuthor.text = author ?: "Unknown"
        binding.textViewYear.text = year ?: "-"

        if (coverId != 0 && coverId != -1) {
            val imageUrl = "https://covers.openlibrary.org/b/id/$coverId-L.jpg"
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.book_not_found) // tampil sementara
                .error(R.drawable.book_not_found)       // kalau gagal load
                .into(binding.imgCover)
        } else {
            // kalau nggak ada coverId
            binding.imgCover.setImageResource(R.drawable.book_not_found)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_AUTHOR = "arg_author"
        private const val ARG_YEAR = "arg_year"
        private const val ARG_COVER_ID = "arg_cover_id"

        fun newInstance(title: String, author: String, year: String, coverId: Int) =
            BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_AUTHOR, author)
                    putString(ARG_YEAR, year)
                    putInt(ARG_COVER_ID, coverId)
                }
            }
    }
}
