package com.example.sambang.Dashboard.Master.Desa.Presenter

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster

interface DataDesaView {

    fun onSuccessDataDesa(data: List<ModelDesaMaster?>?)
    fun onErrorDataDesa(msg: String?)
}