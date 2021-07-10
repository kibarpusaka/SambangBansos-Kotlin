package com.example.sambang.Dashboard.Master.Desa

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Master.Desa.Adapter.AdapterDesa
import com.example.sambang.R
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Master.Desa.Presenter.DataDesaView
import com.example.sambang.Dashboard.Master.Desa.Presenter.DesaPresenter
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_desa_master.*

class DesaMasterActivity : Base(), DataDesaView {
    private lateinit var presenter : DesaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desa_master)
        presenter = DesaPresenter(this)

        refreshDataDesa()
        searchListener()

    }

    private fun refreshDataDesa() {
        presenter.getDataDesa(user)
    }

    private fun searchListener(){
        search_desa_master.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_desa_master.clearFocus()
                if (!query.isNullOrEmpty()){
                    presenter.filterData(query.toString().trim())
                    return true
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null && newText.toString().trim().isEmpty()){
                    presenter.showAllData()
                    return true
                }
                return false
            }
        })
    }

    override fun onSuccessDataDesa(data: List<ModelDesaMaster?>?) {
        rv_desa_master.layoutManager = LinearLayoutManager(applicationContext)
        rv_desa_master.adapter = AdapterDesa(data)
    }

    override fun onErrorDataDesa(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}