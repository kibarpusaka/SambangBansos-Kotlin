package com.example.sambang.Dashboard.Kependudukan.Keluarga

interface DataKeluargaView {

    fun onSuccessDataBarang(data: List<ModelKeluarga>?)
    fun onErrorDataBarang(msg: String?)

    fun onSuccessDeleteBarang(msg : String?)
    fun onErrorDeleteBarang(msg : String?)
}