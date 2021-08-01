package com.example.sambang.Dashboard.Kependudukan.Keluarga

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.AddKeluargaPresenter
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_tambah_keluarga.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddKeluargaActivity : Base(), AddKeluargaView, AdapterView.OnItemSelectedListener {
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
        getDesa()
        acationToolbar()
    }

    private fun acationToolbar() {
        toolbar_tambah_keluarga.setOnClickListener {
            startActivity(Intent(this@AddKeluargaActivity, KeluargaActivity::class.java))
        }
    }

    private fun getDesa() {
        presenter.getDataDesa(sessionManager.getUserToken())
    }

    //EDIT KELUARGA
    @SuppressLint("SetTextI18n")
    private fun actionEdit(serializable: Serializable)
    {
        btn_create_keluarga.text = "Update Data Keluarga"

        val keluarga = serializable as ModelKeluarga
        et_keluarga_nokk.setText(keluarga.nomerkk)
        et_keluarga_alamat.setText(keluarga.alamat)
        et_keluarga_rt.setText(keluarga.rt)
        et_keluarga_rw.setText(keluarga.rw)
        et_keluarga_desa_hide.setText(keluarga.desa)

        btn_create_keluarga.setOnClickListener {
            keluarga.nomerkk = et_keluarga_nokk.text.toString()
            keluarga.alamat = et_keluarga_alamat.text.toString()
            keluarga.rt = et_keluarga_rt.text.toString()
            keluarga.rw = et_keluarga_rw.text.toString()
            keluarga.desa = et_keluarga_desa_hide.text.toString()

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

            presenter.updateKeluarga(sessionManager.getUserToken(),keluarga)
            startActivity(Intent(this@AddKeluargaActivity, KeluargaActivity::class.java))
        }
    }

    //TAMBAH KELUARGA
    @SuppressLint("SetTextI18n")
    private fun actionTambah()
    {
        btn_create_keluarga.setOnClickListener {
            btn_create_keluarga.text = "Tambah Data Kelurga "

            val keluarga = ModelKeluarga()
            keluarga.nomerkk = et_keluarga_nokk.text.toString()
            keluarga.alamat = et_keluarga_alamat.text.toString()
            keluarga.rt = et_keluarga_rt.text.toString()
            keluarga.rw = et_keluarga_rw.text.toString()
            keluarga.desa = et_keluarga_desa_hide.text.toString()

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

            presenter.addKeluarga(sessionManager.getUserToken(), keluarga)
            startActivity(Intent(this@AddKeluargaActivity, KeluargaActivity::class.java))
        }
    }

    override fun onSuccessAddKeluarga(msg: String?)
    {
        toast(msg?:"").show()
//        startActivity(Intent(this@AddKeluargaActivity, KeluargaActivity::class.java))
    }

    override fun onErrorAddKeluarga(msg: String?)
    {

        toast(msg?:"Nomer KK Sudah Terdaftar").show()
    }

    override fun attachSpiner(desa: List<ModelDesaMaster>) {
        val spinerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, desa)
        et_keluarga_desa.apply {
            adapter = spinerAdapter
        }
        et_keluarga_desa.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectObject = parent?.selectedItem as ModelDesaMaster
        et_keluarga_desa_hide.setText(selectObject.id)

//        Toast.makeText(
//                    this@AddKeluargaActivity,
//                    "Id:${selectObject.id} ${selectObject.nama}",
//                    Toast.LENGTH_SHORT).show()
            }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        //....//
    }
}

