package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter

import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ModelDaftarUsulan

interface DaftarUsulanView {
    fun onSuccessDataDaftarUsulan(data: List<ModelNikAktif?>?)
    fun onErrorDataDaftarUsulan(msg: String?)
}