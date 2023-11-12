package com.bengkelsampah.bengkelsampahapp.ui.driver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeDriverBinding
import com.bengkelsampah.bengkelsampahapp.ui.moneybag.MoneyBagActivity
import com.bengkelsampah.bengkelsampahapp.ui.pickupwaste.PickupActivity


class HomeDriverFragment : Fragment() {
    private var _binding: FragmentHomeDriverBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DriverMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDriverBinding.inflate(inflater, container, false)

        setupNavigation()
        return binding.root
    }

    private fun setupNavigation() {
        binding.btnDriverHomeJemputSampah.setOnClickListener {
            val intent = Intent(activity,PickupActivity::class.java)
            startActivity(intent)
        }

        binding.btnDriverHomeArtikel.setOnClickListener {
            //TODO Connect to article list
        }
        binding.btnDriverHomeMoneyBag.setOnClickListener {
            val intent = Intent(activity,MoneyBagActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}