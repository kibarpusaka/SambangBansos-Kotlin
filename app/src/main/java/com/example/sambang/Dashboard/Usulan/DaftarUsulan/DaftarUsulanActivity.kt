package com.example.sambang.Dashboard.Usulan.DaftarUsulan

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Adapter.AdapterDaftarUsulan
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ModelDaftarUsulan
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter.DaftarUsulanPresenter
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter.DaftarUsulanView
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_daftar_usulan.*

class DaftarUsulanActivity : Base(), DaftarUsulanView {
    private lateinit var presenter: DaftarUsulanPresenter
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_usulan)

        presenter = DaftarUsulanPresenter(this)
        sessionManager = SessionManager(this)
        refreshDaftarUsulan()
        searchListener()
    }

    private fun refreshDaftarUsulan() {
        presenter.getDataDesa(sessionManager.getUserToken())
        presenter.getDataKeluarga(sessionManager.getUserToken())
    }

    private fun searchListener(){
        in_search_daftar_usulan.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                in_search_daftar_usulan.clearFocus()
                if (!query.isNullOrEmpty()){
                    presenter.filterData(query.toString().trim())
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.toString().trim().isEmpty()) {
                    presenter.showAllData()
                    return true
                }
                return false
            }
        })
    }

    override fun onSuccessDataDaftarUsulan(data: List<ModelNikAktif?>?) {
        rv_daftar_usulan.layoutManager = LinearLayoutManager(applicationContext)
        rv_daftar_usulan.adapter = AdapterDaftarUsulan(data)

    }

    override fun onErrorDataDaftarUsulan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}