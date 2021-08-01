package com.example.sambang.Dashboard.Kependudukan.CheckNIK

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.Adapter.AdapterCheckNik
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.Presenter.CheckNikPresenter
import com.example.sambang.Dashboard.Kependudukan.Keluarga.KeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.AddNikAktifActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.NikAktifPresenter
import com.example.sambang.MainActivity
import com.example.sambang.R
import com.example.sambang.SharedPref.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_check_nik.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class CheckNikActivity : Base(), DataNikAktifView {
    private lateinit var presenterNikAktif: NikAktifPresenter
    private lateinit var presenter: CheckNikPresenter
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_nik)

        presenter = CheckNikPresenter(this)
        presenterNikAktif = NikAktifPresenter(this)
        sessionManager = SessionManager(this)
        refreshCheckNik()
        searchListener()
        actionToolbar()

    }

    private fun actionToolbar() {
        toolbar_check_nik_kependudukan.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun refreshCheckNik() {
        presenter.getDataDesa(sessionManager.getUserToken())
        presenter.getDataKeluarga(sessionManager.getUserToken())
    }

    private fun searchListener(){
        in_search_check_nik_kependudukan.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                in_search_check_nik_kependudukan.clearFocus()
                if (!query.isNullOrEmpty()){
                    presenter.filterData(query.toString().trim())
                    return true
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.toString().trim().isEmpty()) {
                    presenter.showAllData()
                    return true
                }
                return false
            }
        })
    }

    override fun onSuccessDataNikAktif(data: List<ModelNikAktif?>?) {
        rv_check_nik_kependudukan.layoutManager = LinearLayoutManager(applicationContext)
        rv_check_nik_kependudukan.adapter = AdapterCheckNik(data, object : AdapterCheckNik.OnMenuClicked{
            override fun click(menuItem: MenuItem, checkNik: ModelNikAktif?) {
                when(menuItem.itemId){
                    R.id.editNikAktif -> editNikAktif(checkNik)
                    R.id.hapusNikAktif -> hapusNikAktif(checkNik)
                }
            }

        })
    }

    private fun hapusNikAktif(checkNik: ModelNikAktif?) {
        alert {
            title = "Konfirmasi"
            message = "Yakin akan Menghapus  ${checkNik?.nik}"

            positiveButton("Hapus"){
                presenterNikAktif.deleteNikAktif(sessionManager.getUserToken(), checkNik)
                refreshCheckNik()
//                NikAktifPresenter(this@CheckNikActivity).deleteNikAktif(sessionManager.getUserToken(), checkNik)
            }
            negativeButton("Batal"){}
        }.show()
    }

    private fun editNikAktif(checkNik: ModelNikAktif?) {
        val intent = Intent(this, AddNikAktifActivity::class.java)
        intent.putExtra(TAGS.USER, user )
        intent.putExtra(TAGS.NIKAKTIF, checkNik)
        startActivityForResult(intent,1)

    }

    override fun onErrorDataNikAktif(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteNikAktif(msg: String?) {
        toast(msg ?: "Data Telah Di Hapus").show()
        startActivity(Intent(this@CheckNikActivity, CheckNikActivity::class.java))
    }

    override fun onErrorDeleteNikAktif(msg: String?) {
        toast(msg ?: "Data Sudah Digunakan").show()
    }
}