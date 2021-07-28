package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Adapter.AdapterPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.DataPenerimaBantuanView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.PenerimaBantuanPresenter
import com.example.sambang.Dashboard.Kependudukan.Keluarga.AddKeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.KeluargaPresenter
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Adapter.AdapterDaftarUsulan
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_penerima_bantuan.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class PenerimaBantuanActivity : Base(), DataPenerimaBantuanView {
    private lateinit var presenter: PenerimaBantuanPresenter
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penerima_bantuan)
        presenter = PenerimaBantuanPresenter(this)
        sessionManager = SessionManager(this)

        inAction()
        refreshPenerimaBantuan()
    }

    private fun inAction() {
        in_search_penerima_bantuan.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                in_search_penerima_bantuan.clearFocus()
                if (!query.isNullOrEmpty()){
                    presenter.filterData(query.toString().trim())
                    return false
                }
                return true
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

    private fun refreshPenerimaBantuan() {
        presenter.getDataDesa(sessionManager.getUserToken())
        presenter.getDataKeluarga(sessionManager.getUserToken())
    }

    override fun onSuccessDataPenerimaBantuan(data: List<ModelNikAktif?>?) {
        rv_penerima_bantuan_bantuan.layoutManager = LinearLayoutManager(applicationContext)
        rv_penerima_bantuan_bantuan.adapter = AdapterDaftarUsulan(data)
    }

    override fun onErrorDataPenerimaBantuan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}