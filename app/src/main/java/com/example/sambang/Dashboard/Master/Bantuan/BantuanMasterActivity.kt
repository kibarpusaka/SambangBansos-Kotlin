package com.example.sambang.Dashboard.Master.Bantuan

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Master.Bantuan.Adapter.AdapterBantuanMaster
import com.example.sambang.Dashboard.Master.Bantuan.Data.ModelBantuanMaster
import com.example.sambang.Dashboard.Master.Bantuan.Presenter.BantuanMasterPresenter
import com.example.sambang.Dashboard.Master.Bantuan.Presenter.DataBantuanView
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_bantuan_master.*

class BantuanMasterActivity : Base(), DataBantuanView {
    private lateinit var presenter: BantuanMasterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan_master)
        presenter = BantuanMasterPresenter(this)

        refreshBantuan()
    }

    private fun refreshBantuan() {
        presenter.getBantuan(user)
    }

    override fun onSuccessDataBantuan(data: List<ModelBantuanMaster?>?) {
        rv_bantuan_master.layoutManager = LinearLayoutManager(applicationContext)
        rv_bantuan_master.adapter = AdapterBantuanMaster(data)
    }

    override fun onErrorDataBantuan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}