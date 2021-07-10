package com.example.sambang.Dashboard.Kependudukan.NikAktif

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Kependudukan.Keluarga.AddKeluargaActivity
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Adapter.AdapterNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.DataNikAktifView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Presenter.NikAktifPresenter
import com.example.sambang.R
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_nik_aktif.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NikAktifActivity : Base(), DataNikAktifView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nik_aktif)

//        setActionButton()
        refreshNikAktif()
    }

//    private fun setActionButton() {
//        btAddDataNikAktif.onClick {
//            startActivity<AddNikAktifActivity>(TAGS.USER to user)
//        }
//    }

    private fun refreshNikAktif() {
        NikAktifPresenter(this).getNikAktif(user)
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