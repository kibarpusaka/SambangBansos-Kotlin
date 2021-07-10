package com.example.sambang.Dashboard.Kependudukan.NikAktif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.AddNikAktifPresenter
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_add_nik_aktif.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddNikAktifActivity : Base(), AddNikAktifView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nik_aktif)

        val intent = intent.getSerializableExtra(TAGS.NIKAKTIF)

        if (intent != null){
            actionEditNik(intent)
        }else{
            actionTambahNik()
        }
    }

    private fun actionEditNik(serializable: Serializable) {
        btn_create_nikaktif.text = "Simpan"

        val nikAktif = serializable as ModelNikAktif
        et_nikaktif_nik.setText(nikAktif.nik)
        et_nikaktif_nama.setText(nikAktif.nama)
        et_nikaktif_tempat.setText(nikAktif.tempatlahir)
        et_nikaktif_tanggal.setText(nikAktif.tanggallahir)
        et_nikaktif_alamat.setText(nikAktif.alamat)
        et_nikaktif_rt.setText(nikAktif.rt.toString())
        et_nikaktif_rw.setText(nikAktif.rw.toString())
        et_nikaktif_nikvalid.setText(nikAktif.nikvalid.toString())
        et_nikaktif_desa.setText(nikAktif.desa.toString())
        et_nikaktif_keluarga.setText(nikAktif.keluarga.toString())

        btn_create_nikaktif.setOnClickListener {
            val nik = et_nikaktif_nik.text.toString()
            val nama = et_nikaktif_nama.text.toString()
            val tempat = et_nikaktif_tempat.text.toString()
            val tanggal = et_nikaktif_tanggal.text.toString()
            val alamat = et_nikaktif_alamat.text.toString()
            val rt = et_nikaktif_rt.text.toString()
            val rw = et_nikaktif_rw.text.toString()
            val nikvalid = et_nikaktif_nikvalid.text.toString()
            val desa = et_nikaktif_desa.text.toString()
            val keluarga = et_nikaktif_keluarga.text.toString()

            nikAktif.nik = nik
            nikAktif.nama = nama
            nikAktif.tempatlahir = tempat
            nikAktif.tanggallahir = tanggal
            nikAktif.alamat = alamat
            nikAktif.rt = rt.toInt()
            nikAktif.rw = rw.toInt()
            nikAktif.nikvalid = nikvalid.toBoolean()
            nikAktif.desa = desa.toInt()
            nikAktif.keluarga = keluarga.toInt()

            AddNikAktifPresenter(this@AddNikAktifActivity).updateNikAktif(nikAktif)
        }
    }

    private fun actionTambahNik() {
        btn_create_nikaktif.setOnClickListener {
            btn_create_nikaktif.text = "Tambah"

            val nik = et_nikaktif_nik.text.toString()
            val nama = et_nikaktif_nama.text.toString()
            val tempat = et_nikaktif_tempat.text.toString()
            val tanggal = et_nikaktif_tanggal.text.toString()
            val alamat = et_nikaktif_alamat.text.toString()
            val rt = et_nikaktif_rt.text.toString()
            val rw = et_nikaktif_rw.text.toString()
            val nikvalid = et_nikaktif_nikvalid.text.toString()
            val desa = et_nikaktif_desa.text.toString()
            val keluarga = et_nikaktif_keluarga.text.toString()

            val nikAktif = ModelNikAktif()
            nikAktif.nik = nik
            nikAktif.nama = nama
            nikAktif.tempatlahir = tempat
            nikAktif.tanggallahir = tanggal
            nikAktif.alamat = alamat
            nikAktif.rt = rt.toInt()
            nikAktif.rw = rw.toInt()
            nikAktif.nikvalid = nikvalid.toBoolean()
            nikAktif.desa = desa.toInt()
            nikAktif.keluarga = keluarga.toInt()

            AddNikAktifPresenter(this@AddNikAktifActivity).addNikAktif(nikAktif)
        }

    }

    override fun onSuccessAddNikAktif(msg: String?) {
        toast(msg?:"").show()
        finish()
    }

    override fun onErrorAddNikAktif(msg: String?) {
        toast(msg?:"").show()
    }
}