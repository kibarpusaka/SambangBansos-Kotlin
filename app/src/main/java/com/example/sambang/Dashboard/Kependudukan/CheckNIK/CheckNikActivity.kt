package com.example.sambang.Dashboard.Kependudukan.CheckNIK

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.Adapter.AdapterCheckNik
import com.example.sambang.Dashboard.Kependudukan.CheckNIK.Presenter.CheckNikPresenter
import com.example.sambang.Dashboard.Kependudukan.NikAktif.AddNikAktifActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.NikAktifPresenter
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_check_nik.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class CheckNikActivity : Base(), DataNikAktifView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_nik)

        refreshCheckNik()
    }

    private fun refreshCheckNik() {
        CheckNikPresenter(this).getCheckNik(user)
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
                NikAktifPresenter(this@CheckNikActivity).deleteNikAktif(user, checkNik)
            }
            negativeButton("Batal"){}
        }.show()
        refreshCheckNik()
    }

    private fun editNikAktif(checkNik: ModelNikAktif?) {
        val intent = Intent(this, AddNikAktifActivity::class.java)
        intent.putExtra(TAGS.USER, user )
        intent.putExtra(TAGS.NIKAKTIF, checkNik)
        startActivityForResult(intent,1)
        refreshCheckNik()
    }

    override fun onErrorDataNikAktif(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteNikAktif(msg: String?) {
        toast(msg ?: "Data Telah Di Hapus").show()
        refreshCheckNik()
    }

    override fun onErrorDeleteNikAktif(msg: String?) {
        toast(msg ?: "Data Sudah Digunakan").show()
    }
}