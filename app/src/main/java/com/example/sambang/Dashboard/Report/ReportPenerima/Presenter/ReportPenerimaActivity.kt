package com.example.sambang.Dashboard.Report.ReportPenerima.Presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Adapter.AdapterPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.DataPenerimaBantuanView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.PenerimaBantuanPresenter
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Report.Adapter.AdapterReportPenerima
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Adapter.AdapterDaftarUsulan
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_penerima_bantuan.*

class ReportPenerimaActivity : Base(), DataPenerimaBantuanView {
    private lateinit var sessionManager: SessionManager
    private lateinit var presenter: PenerimaBantuanPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_penerima)

        presenter = PenerimaBantuanPresenter(this)
        sessionManager = SessionManager(this)

        refreshData()
        inSearch()
    }

    private fun inSearch() {
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

    private fun refreshData() {
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