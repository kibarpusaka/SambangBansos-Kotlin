package com.example.sambang.Dashboard.Kependudukan.Keluarga

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.sambang.Utils.Base
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.AddKeluargaPresenter
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import kotlinx.android.synthetic.main.activity_tambah_keluarga.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddKeluargaActivity : Base(), AddKeluargaView
{
    private lateinit var presenter: AddKeluargaPresenter
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_keluarga)
        presenter = AddKeluargaPresenter(this)
        sessionManager = SessionManager(this)

        val intent = intent.getSerializableExtra(TAGS.KELUARGA)

        if (intent != null){
            actionEdit(intent)
        } else {
            actionTambah()
        }


    }
    //EDIT KELUARGA
    private fun actionEdit(serializable: Serializable)
    {
        btn_create_keluarga.text = "Update Keluarga"

        val keluarga = serializable as ModelKeluarga
        et_keluarga_nokk.setText(keluarga.nomerkk)
        et_keluarga_alamat.setText(keluarga.alamat)
        et_keluarga_rt.setText(keluarga.rt)
        et_keluarga_rw.setText(keluarga.rw)
        et_keluarga_desa.setText(keluarga.desa)

        btn_create_keluarga.setOnClickListener {
            keluarga.nomerkk = et_keluarga_nokk.text.toString()
            keluarga.alamat = et_keluarga_alamat.text.toString()
            keluarga.rt = et_keluarga_rt.text.toString()
            keluarga.rw = et_keluarga_rw.text.toString()
            keluarga.desa = et_keluarga_desa.text.toString()

            if (keluarga.nomerkk!!.isEmpty()){
                et_keluarga_nokk.error = "No KK Required"
                et_keluarga_nokk.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.alamat!!.isEmpty()){
                et_keluarga_alamat.error = "Alamat Required"
                et_keluarga_alamat.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.rt!!.toString().isEmpty()){
                et_keluarga_rt.error = "Rt Required"
                et_keluarga_rt.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.rw.toString().isEmpty()){
                et_keluarga_rw.error = "Rw Required"
                et_keluarga_rw.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.desa!!.toString().isEmpty()){
                et_keluarga_desa.error = "Desa Required"
                et_keluarga_desa.requestFocus()
                return@setOnClickListener
            }

            presenter.updateKeluarga(sessionManager.getUserToken(),keluarga)
            startActivity(Intent(this@AddKeluargaActivity, KeluargaActivity::class.java))

        }
    }

    //TAMBAH KELUARGA
    private fun actionTambah()
    {
        btn_create_keluarga.setOnClickListener {
            btn_create_keluarga.text = "Tambah Kelurga "

            val keluarga = ModelKeluarga()
            keluarga.nomerkk = et_keluarga_nokk.text.toString()
            keluarga.alamat = et_keluarga_alamat.text.toString()
            keluarga.rt = et_keluarga_rt.text.toString()
            keluarga.rw = et_keluarga_rw.text.toString()
            keluarga.desa = et_keluarga_desa.text.toString()

            if (keluarga.nomerkk!!.isEmpty()){
                et_keluarga_nokk.error = "No KK Required"
                et_keluarga_nokk.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.alamat!!.isEmpty()){
                et_keluarga_alamat.error = "Alamat Required"
                et_keluarga_alamat.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.rt!!.toString().isEmpty()){
                et_keluarga_rt.error = "Rt Required"
                et_keluarga_rt.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.rw.toString().isEmpty()){
                et_keluarga_rw.error = "Rw Required"
                et_keluarga_rw.requestFocus()
                return@setOnClickListener
            }
            if (keluarga.desa!!.toString().isEmpty()){
                et_keluarga_desa.error = "Desa Required"
                et_keluarga_desa.requestFocus()
                return@setOnClickListener
            }

            presenter.addKeluarga(sessionManager.getUserToken(), keluarga)
            startActivity(Intent(this@AddKeluargaActivity, KeluargaActivity::class.java))
        }
    }

    override fun onSuccessAddKeluarga(msg: String?)
    {
        toast(msg?:"").show()
        finish()
    }

    override fun onErrorAddKeluarga(msg: String?)
    {
        toast(msg?:"Nomer KK Sudah Ada").show()
    }

}