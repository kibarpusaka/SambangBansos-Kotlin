package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Presenter

import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ModelDaftarUsulan

interface DaftarUsulanView {
    fun onSuccessDataDaftarUsulan(data: List<ModelDaftarUsulan?>?)
    fun onErrorDataDaftarUsulan(msg: String?)
}