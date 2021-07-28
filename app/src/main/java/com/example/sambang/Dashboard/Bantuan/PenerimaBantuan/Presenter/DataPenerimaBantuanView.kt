package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter

import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif

interface DataPenerimaBantuanView {
    fun onSuccessDataPenerimaBantuan(data: List<ModelNikAktif?>?)
    fun onErrorDataPenerimaBantuan(msg: String?)

}