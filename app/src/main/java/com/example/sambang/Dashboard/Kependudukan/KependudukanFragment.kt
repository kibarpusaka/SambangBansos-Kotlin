package com.example.sambang.Dashboard.Kependudukan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.CheckNikActivity
import com.example.sambang.Dashboard.Kependudukan.Keluarga.KeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.NikAktifActivity
import com.example.sambang.Dashboard.Kependudukan.NikNonAktif.NikNonAktifActivity
import com.example.sambang.R
import kotlinx.android.synthetic.main.fragment_kependudukan.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KependudukanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KependudukanFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_kependudukan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cv_check_nik.setOnClickListener {
            val check_nik = Intent (this@KependudukanFragment.context, CheckNikActivity::class.java)
            startActivity(check_nik)
        }

        cv_nik_aktif.setOnClickListener {
            val nik_aktif = Intent (this@KependudukanFragment.context, NikAktifActivity::class.java)
            startActivity(nik_aktif)
        }

        cv_keluarga.setOnClickListener {
            val keluarga = Intent (this@KependudukanFragment.context, KeluargaActivity::class.java)
            startActivity(keluarga)
        }

        cv_nik_non_aktif.setOnClickListener {
            val nik_non_aktif = Intent (this@KependudukanFragment.context, NikNonAktifActivity::class.java)
            startActivity(nik_non_aktif)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KependudukanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KependudukanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}