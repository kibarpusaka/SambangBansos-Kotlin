package com.example.sambang.Dashboard.Kependudukan.NikAktif

import android.content.Intent
import android.os.Bundle
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.CheckNikActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.AddNikAktifPresenter
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_add_nik_aktif.*
import kotlinx.android.synthetic.main.activity_tambah_keluarga.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddNikAktifActivity : Base(), AddNikAktifView {
    private lateinit var presenter: AddNikAktifPresenter
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nik_aktif)
        presenter = AddNikAktifPresenter(this)
        sessionManager = SessionManager(this)

        val intent = intent.getSerializableExtra(TAGS.NIKAKTIF)

        if (intent != null){
            actionEditNik(intent)
        }else{
            actionTambahNik()
        }
    }

    private fun actionEditNik(serializable: Serializable) {
        btn_create_nikaktif.text = "Update Warga"

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
            nikAktif.nik = et_nikaktif_nik.text.toString()
            nikAktif.nama = et_nikaktif_nama.text.toString()
            nikAktif.tempatlahir = et_nikaktif_tempat.text.toString()
            nikAktif.tanggallahir = et_nikaktif_tanggal.text.toString()
            nikAktif.alamat = et_nikaktif_alamat.text.toString()
            nikAktif.rt = et_nikaktif_rt.text.toString()
            nikAktif.rw = et_nikaktif_rw.text.toString()
            nikAktif.nikvalid = et_nikaktif_nikvalid.text.toString().toBoolean()
            nikAktif.desa = et_nikaktif_desa.text.toString()
            nikAktif.keluarga = et_nikaktif_keluarga.text.toString()

            if (nikAktif.nik!!.isEmpty()){
                et_nikaktif_nik.error = "Nik Required"
                et_nikaktif_nik.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.nama!!.isEmpty()){
                et_nikaktif_nama.error = "Nama Required"
                et_nikaktif_nama.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.tempatlahir!!.toString().isEmpty()){
                et_nikaktif_tempat.error = "Tempat Lahir Required"
                et_nikaktif_tempat.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.tanggallahir.toString().isEmpty()){
                et_nikaktif_tanggal.error = "Tanggal Lahir Required"
                et_nikaktif_tanggal.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.alamat!!.toString().isEmpty()){
                et_nikaktif_alamat.error = "Alamat Required"
                et_nikaktif_alamat.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.rt!!.toString().isEmpty()){
                et_nikaktif_rt.error = "Rt Required"
                et_nikaktif_rt.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.rw!!.toString().isEmpty()){
                et_nikaktif_rw.error = "Rw Required"
                et_nikaktif_rw.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.nikvalid!!.toString().isEmpty()){
                et_nikaktif_nikvalid.error = "Nik Valid Required"
                et_nikaktif_nikvalid.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.desa!!.toString().isEmpty()){
                et_nikaktif_desa.error = "Desa Required"
                et_nikaktif_desa.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.keluarga!!.toString().isEmpty()){
                et_nikaktif_keluarga.error = "Keluarga Required"
                et_nikaktif_keluarga.requestFocus()
                return@setOnClickListener
            }

            presenter.updateNikAktif(sessionManager.getUserToken(), nikAktif)
            startActivity(Intent(this@AddNikAktifActivity, CheckNikActivity::class.java))
        }
    }

    private fun actionTambahNik() {
        btn_create_nikaktif.setOnClickListener {
            btn_create_nikaktif.text = "Tambah Warga"

            val nikAktif = ModelNikAktif()
            nikAktif.nik = et_nikaktif_nik.text.toString()
            nikAktif.nama = et_nikaktif_nama.text.toString()
            nikAktif.tempatlahir = et_nikaktif_tempat.text.toString()
            nikAktif.tanggallahir = et_nikaktif_tanggal.text.toString()
            nikAktif.alamat = et_nikaktif_alamat.text.toString()
            nikAktif.rt = et_nikaktif_rt.text.toString()
            nikAktif.rw = et_nikaktif_rw.text.toString()
            nikAktif.nikvalid = et_nikaktif_nikvalid.text.toString().toBoolean()
            nikAktif.desa = et_nikaktif_desa.text.toString()
            nikAktif.keluarga = et_nikaktif_keluarga.text.toString()

            if (nikAktif.nik!!.isEmpty()){
                et_nikaktif_nik.error = "Nik Required"
                et_nikaktif_nik.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.nama!!.isEmpty()){
                et_nikaktif_nama.error = "Nama Required"
                et_nikaktif_nama.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.tempatlahir!!.toString().isEmpty()){
                et_nikaktif_tempat.error = "Tempat Lahir Required"
                et_nikaktif_tempat.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.tanggallahir.toString().isEmpty()){
                et_nikaktif_tanggal.error = "Tanggal Lahir Required"
                et_nikaktif_tanggal.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.alamat!!.toString().isEmpty()){
                et_nikaktif_alamat.error = "Alamat Required"
                et_nikaktif_alamat.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.rt!!.toString().isEmpty()){
                et_nikaktif_rt.error = "Rt Required"
                et_nikaktif_rt.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.rw!!.toString().isEmpty()){
                et_nikaktif_rw.error = "Rw Required"
                et_nikaktif_rw.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.nikvalid!!.toString().isEmpty()){
                et_nikaktif_nikvalid.error = "Nik Valid Required"
                et_nikaktif_nikvalid.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.desa!!.toString().isEmpty()){
                et_nikaktif_desa.error = "Desa Required"
                et_nikaktif_desa.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.keluarga!!.toString().isEmpty()){
                et_nikaktif_keluarga.error = "Keluarga Required"
                et_nikaktif_keluarga.requestFocus()
                return@setOnClickListener
            }
            presenter.addNikAktif(sessionManager.getUserToken(), nikAktif)
            startActivity(Intent(this@AddNikAktifActivity, CheckNikActivity::class.java))
        }

    }

    override fun onSuccessAddNikAktif(msg: String?) {
        toast(msg?:"Sukses Tambah").show()
        finish()
        startActivity(Intent(this@AddNikAktifActivity, CheckNikActivity::class.java))
    }

    override fun onErrorAddNikAktif(msg: String?) {
        toast(msg?:"Gagal Tambah").show()
    }
}