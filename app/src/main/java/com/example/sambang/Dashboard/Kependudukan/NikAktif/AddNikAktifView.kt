package com.example.sambang.Dashboard.Kependudukan.NikAktif

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster

interface AddNikAktifView {
    fun onSuccessAddNikAktif(msg: String?)
    fun onErrorAddNikAktif(msg: String?)

    fun attachSpiner(desa : List<ModelDesaMaster>)
    fun attachSpinerKeluarga(keluarga : List<ModelKeluarga>)
}

