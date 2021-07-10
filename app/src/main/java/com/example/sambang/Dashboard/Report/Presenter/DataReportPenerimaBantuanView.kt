package com.example.sambang.Dashboard.Report.Presenter

import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Report.Data.ModelReportPenerima

interface DataReportPenerimaBantuanView {
    fun onSuccessDataPenerimaBantuan(data: List<ModelReportPenerima?>?)
    fun onErrorDataPenerimaBantuan(msg: String?)
}