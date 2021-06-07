package com.example.sambang.Dashboard.Master.Desa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Api.ApiSambang
import com.example.sambang.Dashboard.Master.Desa.Model.ModelDesaMaster
import com.example.sambang.R
import com.example.sambang.Utils.SambangUtils
import kotlinx.android.synthetic.main.activity_desa_master.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DesaMasterActivity : AppCompatActivity() {

    lateinit var desaAdapter: AdapterDesa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desa_master)

        getDesa()
        SetUpAdapter()

    }

    fun getDesa() {
        val desa = SambangUtils().getSambangClientInstance("http://192.168.1.17:1337/").create(ApiSambang::class.java)
        desa.getDesa().enqueue(object : Callback<List<ModelDesaMaster>>{
            override fun onResponse(
                call: Call<List<ModelDesaMaster>>,
                response: Response<List<ModelDesaMaster>>
            ) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<ModelDesaMaster>>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })

    }


    private fun showData(data: List<ModelDesaMaster>){
        val results = data
        desaAdapter.setData(results)
    }

    fun SetUpAdapter() {
        desaAdapter = AdapterDesa(arrayListOf())
        rv_desa_master.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = desaAdapter
        }
    }

}