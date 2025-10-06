package com.example.ifunsoedmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.ifunsoedmobile.R
import com.example.ifunsoedmobile.databinding.ActivityHalaman2Binding

class Halaman2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHalaman2Binding
    private val latitude = "-7.429427"
    private val longitude = "109.338082"
    private val gMapsUrl = "http://maps.google.com/maps?q=loc:"
    private val packageMaps = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHalaman2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
    }

    private fun initLayout() {
        // Set icon dan text untuk layout Lihat Daftar Buku
        binding.layoutKoleksiBuku.imgIcon.setImageResource(R.drawable.ic_book) 
        binding.layoutKoleksiBuku.tvLayout.text = "Lihat Daftar Buku"

        // Set icon dan text untuk layout location
        binding.layoutLocation.imgIcon.setImageResource(R.drawable.ic_location)
        binding.layoutLocation.tvLayout.setText(R.string.alamat)

        // Set icon dan text untuk layout email
        binding.layoutEmail.imgIcon.setImageResource(R.drawable.ic_email)
        binding.layoutEmail.tvLayout.setText(R.string.email)

        // Set icon dan text untuk layout Instagram
        binding.layoutIg.imgIcon.setImageResource(R.drawable.ic_himpunan)
        binding.layoutIg.tvLayout.setText(R.string.ig_himpunan)

        // Set icon dan text untuk layout phone
        binding.layoutPhone.imgIcon.setImageResource(R.drawable.ic_phone)
        binding.layoutPhone.tvLayout.setText(R.string.telepon)

        // Set icon dan text untuk layout website
        binding.layoutWebsite.imgIcon.setImageResource(R.drawable.ic_himpunan)
        binding.layoutWebsite.tvLayout.setText(R.string.website)

        // Set icon dan text untuk layout facebook
        binding.layoutFacebook.imgIcon.setImageResource(R.drawable.ic_himpunan)
        binding.layoutFacebook.tvLayout.setText(R.string.facebook)
    }

    private fun initListener() {
        // Pindah ke DaftarBukuActivity
        binding.layoutKoleksiBuku.root.setOnClickListener {
            startActivity(Intent(this, DaftarBukuActivity::class.java))
        }

        // Open Maps
        binding.layoutLocation.root.setOnClickListener {
            val gMapsIntentUri = "$gMapsUrl$latitude,$longitude".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, gMapsIntentUri)
            startActivity(mapIntent.setPackage(packageMaps))
        }

        // Open Instagram
        binding.layoutIg.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = getString(R.string.ig_himpunan).toUri()
            }
            startActivity(intent)
        }

        // Open Email
        binding.layoutEmail.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${getString(R.string.email)}")
            }
            startActivity(intent)
        }

        // Dial Phone
        binding.layoutPhone.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${getString(R.string.telepon)}")
            }
            startActivity(intent)
        }

        // Website
        binding.layoutWebsite.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://informatika.unsoed.ac.id")
            }
            startActivity(intent)
        }

        // Facebook
        binding.layoutFacebook.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.facebook.com/informatikaunsoed")
            }
            startActivity(intent)
        }

        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
