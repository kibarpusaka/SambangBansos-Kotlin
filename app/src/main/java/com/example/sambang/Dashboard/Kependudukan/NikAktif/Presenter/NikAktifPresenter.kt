package com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter

import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import com.example.sambang.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NikAktifPresenter(val dataNikAktifView: DataNikAktifView)
{
    fun getNikAktif(user: ModelLogin?)
    {
        val sessionManager : SessionManager? = null
        SambangUtils.getservice()
            .getNikAktif(token = "token ${sessionManager?.fetchAuthToken()}")
            .enqueue(object : Callback<ResponNikAktif>
            {
                override fun onResponse(
                    call: Call<ResponNikAktif>,
                    response: Response<ResponNikAktif>
                ) {

                    val body = response.body()
                    if (body?.status == true)
                    {
                        val filtered = body.data_warga?.filter { w -> w.nikvalid == true }
                        dataNikAktifView.onSuccessDataNikAktif(filtered)
                    } else
                    {
                        dataNikAktifView.onErrorDataNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResponNikAktif>, t: Throwable)
                {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
                }
            })
    }

    fun deleteNikAktif(user: ModelLogin?, nikAktif: ModelNikAktif?)
    {
        SambangUtils.getservice()
            .deleteNikAktif(nikAktif?.id)
            .enqueue(object : Callback<ResultSimple>
            {
                override fun onResponse(
                    call: Call<ResultSimple>,
                    response: Response<ResultSimple>
                ) {
                    val body = response.body()
                    if (body?.status == true){
                        dataNikAktifView.onSuccessDeleteNikAktif(body.message)
                    } else {
                        dataNikAktifView.onErrorDeleteNikAktif(body?.message)
                    }
                }

                override fun onFailure(call: Call<ResultSimple>, t: Throwable)
                {
                    dataNikAktifView.onErrorDeleteNikAktif(t.localizedMessage)
                }
            })
    }
}