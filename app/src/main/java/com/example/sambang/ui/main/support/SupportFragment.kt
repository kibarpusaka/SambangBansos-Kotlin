package com.example.sambang.ui.main.support

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sambang.ui.beneficiary.BeneficiaryActivity
import com.example.sambang.R
import com.example.sambang.databinding.FragmentBantuanBinding

class SupportFragment : Fragment(R.layout.fragment_bantuan) {
    private var _binding : FragmentBantuanBinding? = null
    private val binding get() = _binding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBantuanBinding.bind(view)
        setupView()
    }

    private fun setupView(){
        binding?.cvPenerimaBantuan?.setOnClickListener {
            goToBeneficiaryActivity()
        }
    }

    private fun goToBeneficiaryActivity(){
        startActivity(Intent(requireActivity(), BeneficiaryActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}