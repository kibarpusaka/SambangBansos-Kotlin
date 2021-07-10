package com.example.sambang.Dashboard.Master.Bantuan.Presenter

import com.example.sambang.Dashboard.Master.Bantuan.Data.ModelBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster

interface DataBantuanView {
    fun onSuccessDataBantuan(data: List<ModelBantuanMaster?>?)
    fun onErrorDataBantuan(msg: String?)
}