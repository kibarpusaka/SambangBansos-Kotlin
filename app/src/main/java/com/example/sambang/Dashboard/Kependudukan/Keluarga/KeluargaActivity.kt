package com.example.sambang.Dashboard.Kependudukan.Keluarga

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Utils.Base
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Adapter.KeluargaAdapter
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.DataKeluargaView
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.KeluargaPresenter
import com.example.sambang.R
import kotlinx.android.synthetic.main.activity_desa_master.*
import kotlinx.android.synthetic.main.activity_keluarga.*
import kotlinx.android.synthetic.main.activity_tambah_keluarga.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class KeluargaActivity : Base(), DataKeluargaView
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keluarga)

        setActionButton()
        refreshKeluarga()

    }

    private fun setActionButton() {
        btAddDataKeluarga.onClick {
            startActivity<AddKeluargaActivity>(TAGS.USER to user)
        }
    }

    private fun refreshKeluarga()
    {
        KeluargaPresenter(this).getDataKeluarga(user)
    }

    override fun onSuccessDataKeluarga(data: List<ModelKeluarga?>?)
    {
        rv_kluarga_kependudukan.layoutManager = LinearLayoutManager(applicationContext)
        rv_kluarga_kependudukan.adapter = KeluargaAdapter(data, object : KeluargaAdapter.OnMenuClicked{
            override fun click(menuItem: MenuItem, keluarga: ModelKeluarga) {
                when(menuItem.itemId){
                    R.id.editKeluarga -> editKeluarga(keluarga)
                    R.id.hapusKeluarga -> hapusKeluarga(keluarga)
                }
            }

        })
    }

    override fun onErrorDataKeluarga(msg: String?)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun editKeluarga(keluarga: ModelKeluarga)
    {
        val intent = Intent(this, AddKeluargaActivity::class.java)
        intent.putExtra(TAGS.USER, user )
        intent.putExtra(TAGS.KELUARGA, keluarga)
        startActivityForResult(intent,1)
    }

    private fun hapusKeluarga(keluarga: ModelKeluarga)
    {
        alert {
            title = "Konfirmasi"
            message = "Yakin akan Menghapus  ${keluarga.nomerkk}"

            positiveButton("Hapus"){
                KeluargaPresenter(this@KeluargaActivity).deleteKeluarga(user, keluarga)
            }
            negativeButton("Batal"){}
        }.show()
        refreshKeluarga()

    }

    override fun onSuccessDeleteKeluarga(msg: String?)
    {
        toast(msg ?: "Data Sudah Di Hapus").show()
        refreshKeluarga()
    }

    override fun onErrorDeleteKeluarga(msg: String?)
    {
        toast(msg ?: "data sudah digunakan").show()
    }

}