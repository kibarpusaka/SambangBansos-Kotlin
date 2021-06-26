package com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter

import com.example.sambang.Api.ApiSambang
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ResponNikAktif
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.Utils.ResultSimple
import com.example.sambang.Api.SambangUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NikAktifPresenter(val dataNikAktifView: DataNikAktifView)
{
    fun getNikAktif(user : ModelLogin)
    {
        SambangUtils.service()
            .getNikAktif(user.user_id)
            .enqueue(object : Callback<ResponNikAktif>
            {
                override fun onResponse(
                    call: Call<ResponNikAktif>,
                    response: Response<ResponNikAktif>
                ) {
                    val bodyNikAktif = response.body()
                    if (bodyNikAktif?.status == true)
                    {
                        dataNikAktifView.onSuccessDataNikAktif(bodyNikAktif.nikaktif)
                    } else
                    {
                        dataNikAktifView.onErrorDataNikAktif(bodyNikAktif?.message)
                    }
                }

                override fun onFailure(call: Call<ResponNikAktif>, t: Throwable)
                {
                    dataNikAktifView.onErrorDataNikAktif(t.localizedMessage)
                }
            })
    }

    fun deleteNikAktif(user: ModelLogin, nikAktif: ModelNikAktif)
    {
        SambangUtils.service()
            .deleteNikAktif(user.user_id, Integer.parseInt(nikAktif.id.toString()), nikAktif.nik)
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