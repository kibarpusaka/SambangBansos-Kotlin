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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desa_master)

        refreshDataDesa()

    }

    private fun refreshDataDesa() {
        DesaPresenter(this).getDataDesa(user)
    }

    override fun onSuccessDataDesa(data: List<ModelDesaMaster?>?) {
        rv_desa_master.layoutManager = LinearLayoutManager(applicationContext)
        rv_desa_master.adapter = AdapterDesa(data)

        search_desa_master.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_desa_master.clearFocus()
                if (data?.contains(query) == true){

                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun onErrorDataDesa(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}