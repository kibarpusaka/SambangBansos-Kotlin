package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.AddPenerimaBantuanPresenter
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_add_penerima_bantuan.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddPenerimaBantuanActivity : Base(), AddPenerimaBantuanView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_penerima_bantuan)

        val intent = intent.getSerializableExtra(TAGS.PENERIMABANTUAN)

        if (intent != null){
            actionEditPenerima(intent)
        } else {
            actionTambahPenerima()
        }
    }

    private fun actionEditPenerima(serializable: Serializable) {
        btn_create_penerima.text = "Simpan"
        val penerima = serializable as ModelPenerimaBantuan
        et_status_penerima.setText(penerima.status.toString())
        et_tanggal_penerima.setText(penerima.tglpengajuan)
        et_bantuan_penerima.setText(penerima.bantuan.toString())
        et_keluarga_penerima.setText(penerima.keluarga.toString())

        btn_create_penerima.setOnClickListener {
            val status =  et_status_penerima.text.toString()
            val tanggal = et_tanggal_penerima.text.toString()
            val bantuan = et_bantuan_penerima.text.toString()
            val keluarga =  et_keluarga_penerima.text.toString()

            penerima.status = status.toInt()
            penerima.tglpengajuan = tanggal
            penerima.bantuan = bantuan.toInt()
            penerima.keluarga = keluarga.toInt()

            AddPenerimaBantuanPresenter(this@AddPenerimaBantuanActivity).updatePenerimaBantuan(penerima)
        }
    }

    private fun actionTambahPenerima() {
        btn_create_penerima.setOnClickListener {
            btn_create_penerima.text = "Tambah Data"

            val status =  et_status_penerima.text.toString()
            val tanggal = et_tanggal_penerima.text.toString()
            val bantuan = et_bantuan_penerima.text.toString()
            val keluarga =  et_keluarga_penerima.text.toString()

            val penerima = ModelPenerimaBantuan()
            penerima.status = status.toInt()
            penerima.tglpengajuan = tanggal
            penerima.bantuan = bantuan.toInt()
            penerima.keluarga = keluarga.toInt()

            AddPenerimaBantuanPresenter(this@AddPenerimaBantuanActivity).addPenerimaBantuan(penerima)
        }

    }

    override fun onSuccessAddPenerimaBantuan(msg: String?) {
        toast(msg?:"Berhasil Tambah Data").show()
        finish()
    }

    override fun onErrorAddPenerimaBantuan(msg: String?) {
        toast(msg?:"Berhasil Update Data").show()
    }
}