package com.bengkelsampah.bengkelsampahapp.ui.main.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentAccountScreenBinding
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeScreenBinding
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel


class AccountScreenFragment : Fragment() {

    private var _binding: FragmentAccountScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountScreenBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}