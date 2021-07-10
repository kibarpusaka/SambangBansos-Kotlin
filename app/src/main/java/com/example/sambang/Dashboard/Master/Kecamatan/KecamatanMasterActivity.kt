package com.example.sambang.Dashboard.Master.Kecamatan

import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kecamatan_master)

        refreshDataKecamatan()

    }

    private fun refreshDataKecamatan() {
        KecamatanPresenter(this).getDataKecamatan(user)
    }

    override fun onSuccessDataKecamatan(data: List<ModelKecamatanMaster?>?) {
        rv_kecamatan_master.layoutManager = LinearLayoutManager(applicationContext)
        rv_kecamatan_master.adapter = AdapterKecamatan(data)
    }

    override fun onErrorDataKecamatan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}