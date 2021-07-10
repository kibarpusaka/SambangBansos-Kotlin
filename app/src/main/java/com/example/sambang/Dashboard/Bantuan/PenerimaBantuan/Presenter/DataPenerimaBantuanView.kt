package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter

import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga

interface DataPenerimaBantuanView {
    fun onSuccessDataPenerimaBantuan(data: List<ModelPenerimaBantuan?>?)
    fun onErrorDataPenerimaBantuan(msg: String?)

    fun onSuccessDeletePenerimaBantuan(msg : String?)
    fun onErrorDeletePenerimaBantuan(msg : String?)
}