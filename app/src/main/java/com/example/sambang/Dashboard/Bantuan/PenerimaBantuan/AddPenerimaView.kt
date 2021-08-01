package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan

import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Master.Bantuan.Data.ModelBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster

interface AddPenerimaView {
    fun onSuccessAddPenerima(msg: String?)
    fun onErrorAddPenerima(msg: String?)

    fun attachSpiner(desa : List<ModelDesaMaster>)
    fun attachSpinerKeluarga(keluarga : List<ModelKeluarga>)
    fun attachSpinerBantua(bantuan : List<ModelBantuanMaster>)

}