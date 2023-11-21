package com.bengkelsampah.bengkelsampahapp.ui.main.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentAccountScreenBinding
import com.bengkelsampah.bengkelsampahapp.ui.auth.LoginActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel
import com.bengkelsampah.bengkelsampahapp.ui.profile.EditProfileActivity


class AccountScreenFragment : Fragment() {

    private var _binding: FragmentAccountScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountScreenBinding.inflate(layoutInflater)

        navigateToEditProfile()
        logout()
        navigateToLanguageSetting()

        return binding.root
    }

    private fun navigateToLanguageSetting() {
        binding.tvAccountLanguage.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings")
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToEditProfile() {
        binding.btnAccountEdit.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logout() {
        binding.btnAccountLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(activity, LoginActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }
    }

}