package com.example.sambang.Dashboard.Master.Kecamatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Api.ApiSambang
import com.example.sambang.Dashboard.Master.Kecamatan.Adapter.AdapterKecamatan
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster
import com.example.sambang.R
import com.example.sambang.Api.SambangUtils
import kotlinx.android.synthetic.main.activity_kecamatan_master.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KecamatanMasterActivity : AppCompatActivity() {

    lateinit var adapterKecamatan: AdapterKecamatan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kecamatan_master)

        getDataKecamatan()
        setupAdapterKecamatan()
    }



    fun getDataKecamatan(){
        val kecamatan =  SambangUtils.service()
        kecamatan.getKecamatan().enqueue(object : Callback<List<ModelKecamatanMaster>>{
            override fun onResponse(
                call: Call<List<ModelKecamatanMaster>>,
                response: Response<List<ModelKecamatanMaster>>
            ) {
                showDataKecamatan(response.body()!!)
            }

            override fun onFailure(call: Call<List<ModelKecamatanMaster>>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })
    }

    private fun showDataKecamatan(datak: List<ModelKecamatanMaster>){
        val kecamatanD = datak
        adapterKecamatan.setDataKecamatan(kecamatanD)

    }

    fun setupAdapterKecamatan(){
        adapterKecamatan = AdapterKecamatan(arrayListOf())
        rv_kecamatan_master.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterKecamatan
        }
    }
}