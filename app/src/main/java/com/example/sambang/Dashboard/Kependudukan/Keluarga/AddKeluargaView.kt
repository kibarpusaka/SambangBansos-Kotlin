package com.example.sambang.Dashboard.Kependudukan.Keluarga

import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster

interface AddKeluargaView {
    fun onSuccessAddKeluarga(msg: String?)
    fun onErrorAddKeluarga(msg: String?)

//    fun attachSpiner(desa : List<ModelDesaMaster>)
}