package com.example.sambang.Dashboard.Kependudukan.Keluarga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Api.ApiSambang
import com.example.sambang.R
import com.example.sambang.Utils.SambangUtils
import kotlinx.android.synthetic.main.activity_keluarga.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeluargaActivity : AppCompatActivity() {

    lateinit var adapterKeluarga: AdapterKeluarga

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keluarga)

        getKeluarga()
        setupAdapterKeluarga()

    }

    fun getKeluarga(){
        val keluarga = SambangUtils().getSambangClientInstance("http://192.168.1.17:1337/").create(ApiSambang::class.java)
        keluarga.getKeluarga().enqueue(object : Callback<List<ModelKeluarga>>{
            override fun onResponse(
                call: Call<List<ModelKeluarga>>,
                response: Response<List<ModelKeluarga>>
            ) {
                showDataKeluarga(response.body()!!)
            }

            override fun onFailure(call: Call<List<ModelKeluarga>>, t: Throwable) {
                Log.e("Falied", t.message.toString())
            }
        })
    }

    private fun showDataKeluarga(data: List<ModelKeluarga>){
        val keluargaD = data
        adapterKeluarga.setDataKeluarga(keluargaD)

    }

    fun setupAdapterKeluarga(){
        adapterKeluarga = AdapterKeluarga(arrayListOf())
        rv_kluarga_kependudukan.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterKeluarga
        }
    }
}