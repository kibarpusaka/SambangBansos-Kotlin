package com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga

interface DataKeluargaView {

    fun onSuccessDataKeluarga(data: List<ModelKeluarga?>?)
    fun onErrorDataKeluarga(msg: String?)

    fun onSuccessDeleteKeluarga(msg : String?)
    fun onErrorDeleteKeluarga(msg : String?)


}