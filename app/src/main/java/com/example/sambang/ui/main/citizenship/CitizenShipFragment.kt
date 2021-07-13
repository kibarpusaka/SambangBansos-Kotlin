package com.example.sambang.ui.main.citizenship

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.CheckNikActivity
import com.example.sambang.Dashboard.Kependudukan.Keluarga.KeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.NikAktifActivity
import com.example.sambang.Dashboard.Kependudukan.NikNonAktif.NikNonAktifActivity
import com.example.sambang.R
import com.example.sambang.databinding.FragmentKependudukanBinding

class CitizenShipFragment : Fragment(R.layout.fragment_kependudukan) {
    private var _binding : FragmentKependudukanBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentKependudukanBinding.bind(view)
        setupView()
    }

    private fun setupView(){
        binding?.cvCheckNik?.setOnClickListener {
            startActivity(Intent(requireActivity(), CheckNikActivity::class.java))
        }
        binding?.cvNikAktif?.setOnClickListener {
            startActivity(Intent(requireActivity(), NikAktifActivity::class.java))
        }
        binding?.cvNikNonAktif?.setOnClickListener {
            startActivity(Intent(requireActivity(), NikNonAktifActivity::class.java))
        }
        binding?.cvKeluarga?.setOnClickListener {
            startActivity(Intent(requireActivity(), KeluargaActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}