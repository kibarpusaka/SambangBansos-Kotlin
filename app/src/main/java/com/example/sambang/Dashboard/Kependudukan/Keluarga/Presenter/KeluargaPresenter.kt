package com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ResponKeluarga
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeluargaPresenter(val dataKeluargaView: DataKeluargaView)
{

    fun getDataKeluarga(user: ModelLogin?)
    {
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getKeluarga(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponKeluarga>
            {
                override fun onResponse(
                    call: Call<ResponKeluarga>,
                    response: Response<ResponKeluarga>
                )
                {
                    val body = response.body()
                    if (body?.status == true)
                    {
                        dataKeluargaView.onSuccessDataKeluarga(body.data_keluarga)
                    } else
                    {
                        dataKeluargaView.onErrorDataKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponKeluarga>, t: Throwable)
                {
                    dataKeluargaView.onErrorDataKeluarga(t.localizedMessage)
                }
            })
    }

    fun deleteKeluarga(user: ModelLogin?, keluarga: ModelKeluarga)
    {
        SambangUtils.getservice()
            .deleteKeluarga(keluarga.id)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                )
                {
                    val body = response.body()
                    if (body?.status == true)
                    {
                        dataKeluargaView.onSuccessDeleteKeluarga(body.message)
                    } else
                    {
                        dataKeluargaView.onErrorDeleteKeluarga(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    dataKeluargaView.onErrorDeleteKeluarga(t.localizedMessage)
                }
            })
    }
}