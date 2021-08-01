package com.example.sambang.Dashboard.Kependudukan.NikAktif

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Kependudukan.Keluarga.AddKeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Adapter.AdapterNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.NikAktifPresenter
import com.example.sambang.MainActivity
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_nik_aktif.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NikAktifActivity : Base(), DataNikAktifView {
    private lateinit var presenter: NikAktifPresenter
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nik_aktif)
        presenter = NikAktifPresenter(this)
        sessionManager = SessionManager(this)

        setActionButton()
        refreshNikAktif()
        searcListener()
        actionToolbar()
    }

    private fun actionToolbar() {
        toolbar_nik_aktif.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setActionButton() {
        btn_create_nikaktif.setOnClickListener {
            val intent = Intent(this@NikAktifActivity, AddNikAktifActivity::class.java)
            startActivity(intent)
        }
    }
    private fun refreshNikAktif() {
        presenter.getDataDesa(sessionManager.getUserToken())
        presenter.getDataKeluarga(sessionManager.getUserToken())
    }

    private fun searcListener(){
        in_search_nik_aktif.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                in_search_nik_aktif.clearFocus()
                if (!query.isNullOrEmpty()){
                    presenter.filterData(query.toString().trim())
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.toString().trim().isEmpty()) {
                    presenter.showDataNikAktif()
                    return true
                }
                return false
            }
        })
    }

    override fun onSuccessDataNikAktif(data: List<ModelNikAktif?>?) {
        rv_nik_aktif.layoutManager = LinearLayoutManager(applicationContext)
        rv_nik_aktif.adapter = AdapterNikAktif(data)
//        rv_nik_aktif.adapter = AdapterNikAktif(data, object : AdapterNikAktif.OnMenuClicked{
//            override fun click(menuItem: MenuItem, nikAktif: ModelNikAktif?) {
//                when(menuItem.itemId){
//                    R.id.editNikAktif -> editNikAktif(nikAktif)
//                    R.id.hapusNikAktif -> hapusNikAktif(nikAktif)
//                }
//            }
//        })
    }



    override fun onErrorDataNikAktif(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

//    private fun hapusNikAktif(nikAktif: ModelNikAktif?) {
//        alert {
//            title = "Konfirmasi"
//            message = "Yakin akan Menghapus  ${nikAktif?.nik}"
//
//            positiveButton("Hapus"){
//                NikAktifPresenter(this@NikAktifActivity).deleteNikAktif(user, nikAktif)
//            }
//            negativeButton("Batal"){}
//        }.show()
//        refreshNikAktif()
//    }
//
//    private fun editNikAktif(nikAktif: ModelNikAktif?) {
//        val intent = Intent(this, AddNikAktifActivity::class.java)
//        intent.putExtra(TAGS.USER, user )
//        intent.putExtra(TAGS.NIKAKTIF, nikAktif)
//        startActivityForResult(intent,1)
//    }

    override fun onSuccessDeleteNikAktif(msg: String?) {
        toast(msg ?: "Data Telah Di Hapus").show()
        refreshNikAktif()
    }

    override fun onErrorDeleteNikAktif(msg: String?) {
        toast(msg ?: "Data Sudah Digunakan").show()

    }
}