package com.example.sambang.Dashboard.Kependudukan.NikAktif

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.CheckNikActivity
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.AddNikAktifPresenter
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_add_nik_aktif.*
import kotlinx.android.synthetic.main.activity_tambah_keluarga.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class AddNikAktifActivity : Base(), AddNikAktifView {
    private lateinit var presenter: AddNikAktifPresenter
    private lateinit var sessionManager: SessionManager
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var formatDate : SimpleDateFormat

    private fun showDateDialog(){
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(this,
            { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[year, monthOfYear] = dayOfMonth
                et_nikaktif_tanggal.setText(formatDate.format(newDate.time))
            },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nik_aktif)

        presenter = AddNikAktifPresenter(this)
        sessionManager = SessionManager(this)
        formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val intent = intent.getSerializableExtra(TAGS.NIKAKTIF)

        if (intent != null){
            actionEditNik(intent)
        }else{
            actionTambahNik()
        }
        getDataSpin()
        actionToolbar()
    }

    private fun actionToolbar() {
        toolbar_addnikaktif.setOnClickListener {
            startActivity(Intent(this,NikAktifActivity::class.java))
        }
    }


    private fun getDataSpin() {
        presenter.getDataDesa(sessionManager.getUserToken())
        presenter.getDataKeluarga(sessionManager.getUserToken())
    }


    @SuppressLint("SetTextI18n")
    private fun actionEditNik(serializable: Serializable) {
        btn_create_nikaktif.text = "Update Data Warga"

        val nikAktif = serializable as ModelNikAktif
        et_nikaktif_nik.setText(nikAktif.nik)
        et_nikaktif_nama.setText(nikAktif.nama)
        et_nikaktif_tempat.setText(nikAktif.tempatlahir)
        et_nikaktif_tanggal.setText(nikAktif.tanggallahir)
        et_nikaktif_alamat.setText(nikAktif.alamat)
        et_nikaktif_rt.setText(nikAktif.rt)
        et_nikaktif_rw.setText(nikAktif.rw)
        et_nikaktif_nikvalid.selectedItem
        et_nikaktif_desa_hide.setText(nikAktif.desa)
        et_nikaktif_keluarga_hide.setText(nikAktif.keluarga)

        btn_create_nikaktif.setOnClickListener {
            nikAktif.nik = et_nikaktif_nik.text.toString()
            nikAktif.nama = et_nikaktif_nama.text.toString()
            nikAktif.tempatlahir = et_nikaktif_tempat.text.toString()
            nikAktif.tanggallahir = et_nikaktif_tanggal.text.toString()
            nikAktif.alamat = et_nikaktif_alamat.text.toString()
            nikAktif.rt = et_nikaktif_rt.text.toString()
            nikAktif.rw = et_nikaktif_rw.text.toString()
            nikAktif.nikvalid = et_nikaktif_nikvalid.selectedItem.toString().toBoolean()
            nikAktif.desa = et_nikaktif_desa_hide.text.toString()
            nikAktif.keluarga = et_nikaktif_keluarga_hide.text.toString()


            if (nikAktif.nik!!.isEmpty()){
                et_nikaktif_nik.error = "Nik Kosong"
                et_nikaktif_nik.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.nama!!.isEmpty()){
                et_nikaktif_nama.error = "Nama Kosong"
                et_nikaktif_nama.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.tempatlahir!!.toString().isEmpty()){
                et_nikaktif_tempat.error = "Tempat Lahir Kosong"
                et_nikaktif_tempat.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.tanggallahir.toString().isEmpty()){
                et_nikaktif_tanggal.error = "Tanggal Lahir Kosong"
                et_nikaktif_tanggal.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.alamat!!.toString().isEmpty()){
                et_nikaktif_alamat.error = "Alamat Kosong"
                et_nikaktif_alamat.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.rt!!.toString().isEmpty()){
                et_nikaktif_rt.error = "Rt Kosong"
                et_nikaktif_rt.requestFocus()
                return@setOnClickListener
            }
            if (nikAktif.rw!!.toString().isEmpty()){
                et_nikaktif_rw.error = "Rw Kosong"
                et_nikaktif_rw.requestFocus()
                return@setOnClickListener
            }
            presenter.updateNikAktif(sessionManager.getUserToken(), nikAktif)
            startActivity(Intent(this@AddNikAktifActivity, CheckNikActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun actionTambahNik() {
        btn_create_nikaktif.onClick {
            btn_create_nikaktif.text = "Tambah Data Warga"

            val nikAktif = ModelNikAktif()
            nikAktif.nik = et_nikaktif_nik.text.toString()
            nikAktif.nama = et_nikaktif_nama.text.toString()
            nikAktif.tempatlahir = et_nikaktif_tempat.text.toString()
            nikAktif.tanggallahir = et_nikaktif_tanggal.text.toString()
            nikAktif.alamat = et_nikaktif_alamat.text.toString()
            nikAktif.rt = et_nikaktif_rt.text.toString()
            nikAktif.rw = et_nikaktif_rw.text.toString()
            nikAktif.nikvalid = et_nikaktif_nikvalid.selectedItem.toString().toBoolean()
            nikAktif.desa = et_nikaktif_desa_hide.text.toString()
            nikAktif.keluarga = et_nikaktif_keluarga_hide.text.toString()

            if (nikAktif.nik!!.isEmpty()){
                et_nikaktif_nik.error = "Nik Kosong"
                et_nikaktif_nik.requestFocus()
                return@onClick
            }
            if (nikAktif.nama!!.isEmpty()){
                et_nikaktif_nama.error = "Nama Kosong"
                et_nikaktif_nama.requestFocus()
                return@onClick

            }
            if (nikAktif.tempatlahir!!.toString().isEmpty()){
                et_nikaktif_tempat.error = "Tempat Lahir Kosong"
                et_nikaktif_tempat.requestFocus()
                return@onClick

            }
            if (nikAktif.tanggallahir.toString().isEmpty()){
                et_nikaktif_tanggal.error = "Tanggal Lahir Kosong"
                et_nikaktif_tanggal.requestFocus()
                return@onClick

            }
            if (nikAktif.alamat!!.toString().isEmpty()){
                et_nikaktif_alamat.error = "Alamat Kosong"
                et_nikaktif_alamat.requestFocus()
                return@onClick

            }
            if (nikAktif.rt!!.toString().isEmpty()){
                et_nikaktif_rt.error = "Rt Kosong"
                et_nikaktif_rt.requestFocus()
                return@onClick

            }
            if (nikAktif.rw!!.toString().isEmpty()){
                et_nikaktif_rw.error = "Rw Kosong"
                et_nikaktif_rw.requestFocus()
                return@onClick

            }

            presenter.addNikAktif(sessionManager.getUserToken(), nikAktif)
            startActivity(Intent(this@AddNikAktifActivity, CheckNikActivity::class.java))
        }

        formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        et_nikaktif_tanggal.setOnClickListener {
            showDateDialog()
        }

        et_nikaktif_nikvalid.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                et_nikaktif_nikvalid.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onSuccessAddNikAktif(msg: String?) {
        toast(msg?:"Sukses Tambah").show()
        finish()
        startActivity(Intent(this@AddNikAktifActivity, CheckNikActivity::class.java))
    }

    override fun onErrorAddNikAktif(msg: String?) {
        toast(msg?:"Periksa Kembali Nik").show()
    }

    override fun attachSpiner(desa: List<ModelDesaMaster>) {
        val spinDesa = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, desa)
        et_nikaktif_desa.apply {
            adapter = spinDesa
        }
        et_nikaktif_desa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectObject = parent?.selectedItem as ModelDesaMaster
                et_nikaktif_desa_hide.setText(selectObject.id)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun attachSpinerKeluarga(keluarga: List<ModelKeluarga>) {
        val spinKeluarga = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, keluarga)
        et_nikaktif_keluarga.apply {
            adapter = spinKeluarga
        }
        et_nikaktif_keluarga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectObjectKeluarga = parent?.selectedItem as ModelKeluarga
                et_nikaktif_keluarga_hide.setText(selectObjectKeluarga.id)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}