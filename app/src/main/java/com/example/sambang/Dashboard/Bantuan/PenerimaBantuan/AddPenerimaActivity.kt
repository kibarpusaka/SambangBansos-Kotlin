package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Presenter.AddPenerimaPresenter
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.CheckNikActivity
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.AddNikAktifPresenter
import com.example.sambang.Dashboard.Master.Bantuan.Data.ModelBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.Dashboard.Report.ReportPenerima.Presenter.ReportPenerimaActivity
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_add_nik_aktif.*
import kotlinx.android.synthetic.main.activity_add_nik_aktif.btn_create_nikaktif
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_alamat
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_desa_hide
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_keluarga_hide
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_nama
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_nik
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_nikvalid
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_rt
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_rw
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_tanggal
import kotlinx.android.synthetic.main.activity_add_nik_aktif.et_nikaktif_tempat
import kotlinx.android.synthetic.main.activity_add_penerima.*
import kotlinx.android.synthetic.main.activity_add_penerima_bantuan.*
import org.jetbrains.anko.toast
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class AddPenerimaActivity : Base(), AddPenerimaView {
    private lateinit var presenter: AddPenerimaPresenter
    private lateinit var sessionManager: SessionManager
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var formatDate : SimpleDateFormat
    private fun showDateDialog(){
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(this,
            { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[year, monthOfYear] = dayOfMonth
                et_bantuan_tanggal.setText(formatDate.format(newDate.time))
            },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_penerima)

        presenter = AddPenerimaPresenter(this)
        sessionManager = SessionManager(this)
        formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val intent = intent.getSerializableExtra(TAGS.PENERIMABANTUAN)

        if (intent != null){
            actionEditPenerima(intent)
        }else{
            actionTambahPenerima()
        }
        getDataSpin()
        actionToolbar()
    }

    private fun actionToolbar() {
        toolbar_addPenerima.setOnClickListener {
            startActivity(Intent(this, PenerimaBantuanActivity::class.java))
        }
    }

    private fun getDataSpin() {
        presenter.getDataDesa(sessionManager.getUserToken())
        presenter.getDataKeluarga(sessionManager.getUserToken())
        presenter.getBantuan(sessionManager.getUserToken())
    }

    private fun actionTambahPenerima() {
        TODO("Not yet implemented")
    }

    private fun actionEditPenerima(serializable: Serializable) {
        btn_create_nikaktif.text = "Update Data Penerima"

        val penerima = serializable as ModelNikAktif
        et_bantuan_nik.setText(penerima.nik)
        et_bantuan_nama.setText(penerima.nama)
        et_bantuan_tempat.setText(penerima.tempatlahir)
        et_bantuan_tanggal.setText(penerima.tanggallahir)
        et_bantuan_alamat.setText(penerima.alamat)
        et_bantuan_rt.setText(penerima.rt)
        et_bantuan_rw.setText(penerima.rw)
        et_bantuan_nikvalid.selectedItem
        et_bantuan_desa_hide.setText(penerima.desa)
        et_bantuan_keluarga_hide.setText(penerima.keluarga)
        et_bantuan_hide.setText(penerima.bantuan)

        btn_create_nikaktif.setOnClickListener {
            penerima.nik = et_bantuan_nik.text.toString()
            penerima.nama = et_bantuan_nama.text.toString()
            penerima.tempatlahir = et_bantuan_tempat.text.toString()
            penerima.tanggallahir = et_bantuan_tanggal.text.toString()
            penerima.alamat = et_bantuan_alamat.text.toString()
            penerima.rt = et_bantuan_rt.text.toString()
            penerima.rw = et_bantuan_rw.text.toString()
            penerima.nikvalid = et_bantuan_nikvalid.selectedItem.toString().toBoolean()
            penerima.desa = et_bantuan_desa_hide.text.toString()
            penerima.keluarga = et_bantuan_keluarga_hide.text.toString()
            penerima.bantuan = et_bantuan_hide.text.toString()


            if (penerima.nik!!.isEmpty()){
                et_bantuan_nik.error = "Nik Kosong"
                et_bantuan_nik.requestFocus()
                return@setOnClickListener
            }
            if (penerima.nama!!.isEmpty()){
                et_bantuan_nama.error = "Nama Kosong"
                et_bantuan_nama.requestFocus()
                return@setOnClickListener
            }
            if (penerima.tempatlahir!!.toString().isEmpty()){
                et_bantuan_tempat.error = "Tempat Lahir Kosong"
                et_bantuan_tempat.requestFocus()
                return@setOnClickListener
            }
            if (penerima.tanggallahir.toString().isEmpty()){
                et_bantuan_tanggal.error = "Tanggal Lahir Kosong"
                et_bantuan_tanggal.requestFocus()
                return@setOnClickListener
            }
            if (penerima.alamat!!.toString().isEmpty()){
                et_bantuan_alamat.error = "Alamat Kosong"
                et_bantuan_alamat.requestFocus()
                return@setOnClickListener
            }
            if (penerima.rt!!.toString().isEmpty()){
                et_bantuan_rt.error = "Rt Kosong"
                et_bantuan_rt.requestFocus()
                return@setOnClickListener
            }
            if (penerima.rw!!.toString().isEmpty()){
                et_bantuan_rw.error = "Rw Kosong"
                et_bantuan_rw.requestFocus()
                return@setOnClickListener
            }
            presenter.updateNikAktif(sessionManager.getUserToken(), penerima)
            startActivity(Intent(this@AddPenerimaActivity, ReportPenerimaActivity::class.java))
        }
        formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        et_bantuan_tanggal.setOnClickListener {
            showDateDialog()
        }
    }

    override fun onSuccessAddPenerima(msg: String?) {
        toast(msg?:"Sukses Tambah").show()
        finish()
    }

    override fun onErrorAddPenerima(msg: String?) {
        toast(msg?:"Gagal Tambah").show()
    }

    override fun attachSpiner(desa: List<ModelDesaMaster>) {
        val spinDesa = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, desa)
        et_bantuan_desa.apply {
            adapter = spinDesa
        }
        et_bantuan_desa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectObject = parent?.selectedItem as ModelDesaMaster
                et_bantuan_desa_hide.setText(selectObject.id)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun attachSpinerKeluarga(keluarga: List<ModelKeluarga>) {
        val spinKeluarga = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, keluarga)
        et_bantuan_keluarga.apply {
            adapter = spinKeluarga
        }
        et_bantuan_keluarga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectObjectKeluarga = parent?.selectedItem as ModelKeluarga
                et_bantuan_keluarga_hide.setText(selectObjectKeluarga.id)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun attachSpinerBantua(bantuan: List<ModelBantuanMaster>) {
        val spinBantuan = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bantuan)
        et_bantuan_bantuan.apply {
            adapter = spinBantuan
        }
        et_bantuan_bantuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectObjectBantuan = parent?.selectedItem as ModelBantuanMaster
                et_bantuan_hide.setText(selectObjectBantuan.id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}