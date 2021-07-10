package com.example.sambang.Dashboard.Kependudukan.NikNonAktif

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikNonAktif.Adapter.AdapterNikNonAktif
import com.example.sambang.Dashboard.Kependudukan.NikNonAktif.Presenter.NikNonAktifPresenter
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_nik_non_aktif.*

class NikNonAktifActivity : Base(), DataNikAktifView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nik_non_aktif)

        refreshNikNonAktif()
    }

    private fun refreshNikNonAktif() {
        NikNonAktifPresenter(this).getNikNonAktif(user)
    }

    override fun onSuccessDataNikAktif(data: List<ModelNikAktif?>?) {
        rv_nik_non_aktif.layoutManager = LinearLayoutManager(applicationContext)
        rv_nik_non_aktif.adapter = AdapterNikNonAktif(data)
    }

    override fun onErrorDataNikAktif(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteNikAktif(msg: String?) {

    }

    override fun onErrorDeleteNikAktif(msg: String?) {

    }
}