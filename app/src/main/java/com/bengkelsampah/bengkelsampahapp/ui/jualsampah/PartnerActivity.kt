package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerDummyData

class PartnerActivity : AppCompatActivity() {

    private val partnerAdapter = PartnerAdapter { _, _ ->
        // Tindakan ketika item di klik
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partner)

        // Inisialisasi RecyclerView dan Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.rv_mitra)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = partnerAdapter

        // Dapatkan data dummy dan tampilkan di RecyclerView
        val dummyData = PartnerDummyData.getDummyPartnerList()
        partnerAdapter.submitData(dummyData)
    }
}