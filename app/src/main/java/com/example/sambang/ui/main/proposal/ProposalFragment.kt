package com.example.sambang.ui.main.proposal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sambang.ui.list_of_propsal.ListOfProposalActivity
import com.example.sambang.ui.propose.PengajuanActivity
import com.example.sambang.R
import com.example.sambang.databinding.FragmentUsulanBinding

class ProposalFragment : Fragment(R.layout.fragment_usulan) {
    private var _binding : FragmentUsulanBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUsulanBinding.bind(view)
        onProposalListTapped()
        onProposalTapped()
    }

    private fun onProposalTapped(){
        binding?.cvPengajuanUsulan?.setOnClickListener {
            startActivity(Intent(requireActivity(), PengajuanActivity::class.java))
        }
    }

    private fun onProposalListTapped(){
        binding?.cvDataUsulanUsulan?.setOnClickListener {
            startActivity(Intent(requireActivity(), ListOfProposalActivity::class.java))
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}