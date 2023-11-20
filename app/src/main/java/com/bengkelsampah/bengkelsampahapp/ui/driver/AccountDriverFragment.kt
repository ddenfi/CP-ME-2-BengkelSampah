package com.bengkelsampah.bengkelsampahapp.ui.driver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentAccountDriverBinding
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentAccountScreenBinding
import com.bengkelsampah.bengkelsampahapp.ui.auth.LoginActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel
import com.bengkelsampah.bengkelsampahapp.ui.profile.EditProfileActivity


class AccountDriverFragment : Fragment() {
    private var _binding: FragmentAccountDriverBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DriverMainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountDriverBinding.inflate(layoutInflater)

        navigateToEditProfile()
        logout()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToEditProfile() {
        binding.btnDriverAccountEdit.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logout() {
        binding.btnDriverAccountLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(activity, LoginActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }
    }

}