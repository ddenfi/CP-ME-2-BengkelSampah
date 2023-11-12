package com.bengkelsampah.bengkelsampahapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.bengkelsampah.bengkelsampahapp.R
import com.google.android.material.appbar.AppBarLayout

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val appBarLayout: AppBarLayout = findViewById(R.id.appBar)
        val toolbar: Toolbar = findViewById(R.id.top_app_bar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}