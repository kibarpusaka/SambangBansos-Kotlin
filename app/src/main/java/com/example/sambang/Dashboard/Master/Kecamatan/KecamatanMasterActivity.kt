package com.example.sambang.Dashboard.Master.Kecamatan

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Master.Kecamatan.Adapter.AdapterKecamatan
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster
import com.example.sambang.R
import com.example.sambang.Dashboard.Master.Kecamatan.Presenter.DataKecamatanView
import com.example.sambang.Dashboard.Master.Kecamatan.Presenter.KecamatanPresenter
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_kecamatan_master.*

class KecamatanMasterActivity : Base(), DataKecamatanView{
    private lateinit var presenter: KecamatanPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kecamatan_master)
        presenter = KecamatanPresenter(this)

        refreshDataKecamatan()
        searchListenerKecamatan()

    }

    private fun refreshDataKecamatan() {
        presenter.getDataKecamatan(user)
    }

    private fun searchListenerKecamatan() {
        in_search_kecamatan_master.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                in_search_kecamatan_master.clearFocus()
                if (!query.isNullOrEmpty()){
                    presenter.filterData(query.toString().trim())
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.toString().trim().isEmpty()){
                    presenter.showAllData()
                    return true
                }
                return false
            }

        })
    }


    override fun onSuccessDataKecamatan(data: List<ModelKecamatanMaster?>?) {
        rv_kecamatan_master.layoutManager = LinearLayoutManager(applicationContext)
        rv_kecamatan_master.adapter = AdapterKecamatan(data)
    }

    override fun onErrorDataKecamatan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}