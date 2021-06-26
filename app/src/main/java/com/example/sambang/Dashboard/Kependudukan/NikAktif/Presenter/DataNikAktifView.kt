package com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter

import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif

interface DataNikAktifView {
    fun onSuccessDataNikAktif(data: List<ModelNikAktif?>?)
    fun onErrorDataNikAktif(msg: String?)

    fun onSuccessDeleteNikAktif(msg : String?)
    fun onErrorDeleteNikAktif(msg : String?)
}