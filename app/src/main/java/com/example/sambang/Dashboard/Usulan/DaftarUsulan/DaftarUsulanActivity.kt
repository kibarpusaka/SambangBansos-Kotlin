package com.example.sambang.Dashboard.Usulan.DaftarUsulan

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Adapter.AdapterDaftarUsulan
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ModelDaftarUsulan
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter.DaftarUsulanPresenter
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter.DaftarUsulanView
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_daftar_usulan.*

class DaftarUsulanActivity : Base(), DaftarUsulanView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_usulan)

        refreshDaftarUsulan()
    }

    private fun refreshDaftarUsulan() {
        DaftarUsulanPresenter(this).getDataDaftarUsulan(user)
    }

    override fun onSuccessDataDaftarUsulan(data: List<ModelDaftarUsulan?>?) {
        rv_daftar_usulan.layoutManager = LinearLayoutManager(applicationContext)
        rv_daftar_usulan.adapter = AdapterDaftarUsulan(data)

    }

    override fun onErrorDataDaftarUsulan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}