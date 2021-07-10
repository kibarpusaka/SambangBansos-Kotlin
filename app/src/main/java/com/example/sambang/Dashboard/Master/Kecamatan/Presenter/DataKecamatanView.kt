package com.example.sambang.Dashboard.Master.Kecamatan.Presenter

import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster

interface DataKecamatanView {
    fun onSuccessDataKecamatan(data: List<ModelKecamatanMaster?>?)
    fun onErrorDataKecamatan(msg: String?)
}