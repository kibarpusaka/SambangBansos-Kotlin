package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Adapter.AdapterPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.DataPenerimaBantuanView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.PenerimaBantuanPresenter
import com.example.sambang.Dashboard.Kependudukan.Keluarga.AddKeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.KeluargaPresenter
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_penerima_bantuan.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class PenerimaBantuanActivity : Base(), DataPenerimaBantuanView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penerima_bantuan)

        inAction()
        refreshPenerimaBantuan()
    }

    private fun inAction() {

    }

    private fun refreshPenerimaBantuan() {
        PenerimaBantuanPresenter(this).getDataPenerimaBantuan(user)
    }

    override fun onSuccessDataPenerimaBantuan(data: List<ModelPenerimaBantuan?>?) {
        rv_penerima_bantuan_bantuan.layoutManager = LinearLayoutManager(applicationContext)
        rv_penerima_bantuan_bantuan.adapter = AdapterPenerimaBantuan(data, object : AdapterPenerimaBantuan.OnMenuClicked{
            override fun click(menuItem: MenuItem, penerimaBantuan: ModelPenerimaBantuan) {
                when(menuItem.itemId){
                    R.id.editPenerimaBantuan -> editPenerimaBantuan(penerimaBantuan)
                    R.id.hapusPenerimaBantuan -> hapusPenerimaBantuan(penerimaBantuan)
                }
            }

        })
    }

    private fun hapusPenerimaBantuan(penerimaBantuan: ModelPenerimaBantuan) {
        alert {
            title = "Konfirmasi"
            message = "Yakin akan Menghapus  ${penerimaBantuan.status}"

            positiveButton("Hapus"){
                PenerimaBantuanPresenter(this@PenerimaBantuanActivity).deletePenerimaBantuan(user, penerimaBantuan)
            }
            negativeButton("Batal"){}
        }.show()
        refreshPenerimaBantuan()
    }

    private fun editPenerimaBantuan(penerimaBantuan: ModelPenerimaBantuan) {
        val intent = Intent(this, AddPenerimaBantuanActivity::class.java)
        intent.putExtra(TAGS.USER, user )
        intent.putExtra(TAGS.PENERIMABANTUAN, penerimaBantuan)
        startActivityForResult(intent,1)
    }

    override fun onErrorDataPenerimaBantuan(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeletePenerimaBantuan(msg: String?) {
        toast(msg ?: "Data Sudah Di Hapus").show()
        refreshPenerimaBantuan()
    }

    override fun onErrorDeletePenerimaBantuan(msg: String?) {
        toast(msg ?: "data sudah Di Hapus").show()
    }
}