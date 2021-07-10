package com.example.sambang.Dashboard.Bantuan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sambang.Dashboard.Bantuan.Pencarian.PencarianDataBantuanActivity
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.PenerimaBantuanActivity
import com.example.sambang.R
import kotlinx.android.synthetic.main.fragment_bantuan.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BantuanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BantuanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bantuan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cv_penerima_bantuan.setOnClickListener {
            val penerimaBantuan = Intent (this@BantuanFragment.context, PenerimaBantuanActivity::class.java)
            startActivity(penerimaBantuan)
        }

//        cv_pencarian_data_bantuan.setOnClickListener {
//            val pencarianpenerima = Intent (this@BantuanFragment.context, PencarianDataBantuanActivity::class.java)
//            startActivity(pencarianpenerima)
//        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BantuanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BantuanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}