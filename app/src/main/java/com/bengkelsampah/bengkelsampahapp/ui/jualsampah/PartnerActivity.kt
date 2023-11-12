package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerDummyData
import com.google.android.material.appbar.AppBarLayout

class PartnerActivity : AppCompatActivity() {

    private val partnerAdapter = PartnerAdapter { _, _ ->

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partner)

        val appBarLayout: AppBarLayout = findViewById(R.id.appBar)
        val toolbar: Toolbar = findViewById(R.id.top_app_bar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_mitra)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = partnerAdapter

        val dummyData = PartnerDummyData.getDummyPartnerList()
        partnerAdapter.submitData(dummyData)
    }
}