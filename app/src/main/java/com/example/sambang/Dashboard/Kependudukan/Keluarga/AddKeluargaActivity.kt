package com.example.sambang.Dashboard.Kependudukan.Keluarga

import android.os.Bundle
import com.example.sambang.Utils.Base
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Presenter.AddKeluargaPresenter
import com.example.sambang.R
import kotlinx.android.synthetic.main.activity_tambah_keluarga.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddKeluargaActivity : Base(), AddKeluargaView
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_keluarga)

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
        btn_create_keluarga.text = "Simpan"

        val keluarga = serializable as ModelKeluarga
        et_keluarga_nokk.setText(keluarga.nomerkk)
        et_keluarga_alamat.setText(keluarga.alamat)
        et_keluarga_rt.setText(keluarga.rt.toString())
        et_keluarga_rw.setText(keluarga.rw.toString())
        et_keluarga_desa.setText(keluarga.desa.toString())

        btn_create_keluarga.setOnClickListener {
            val nomerKK = et_keluarga_nokk.text.toString()
            val alamat = et_keluarga_alamat.text.toString()
            val rt = et_keluarga_rt.text.toString()
            val rw = et_keluarga_rw.text.toString()
            val desa = et_keluarga_desa.text.toString()

            keluarga.nomerkk = nomerKK
            keluarga.alamat = alamat
            keluarga.rt = rt.toInt()
            keluarga.rw = rw.toInt()
            keluarga.desa = desa.toInt()

            AddKeluargaPresenter(this@AddKeluargaActivity).updateKeluarga(keluarga)
        }
    }

    //TAMBAH KELUARGA
    private fun actionTambah()
    {
        btn_create_keluarga.setOnClickListener {
            btn_create_keluarga.text = "Tambah Kelurga "

            val nomerKK = et_keluarga_nokk.text.toString()
            val alamat = et_keluarga_alamat.text.toString()
            val rt = et_keluarga_rt.text.toString()
            val rw = et_keluarga_rw.text.toString()
            val desa = et_keluarga_desa.text.toString()

            val keluarga = ModelKeluarga()
            keluarga.nomerkk = nomerKK
            keluarga.alamat = alamat
            keluarga.rt = rt.toInt()
            keluarga.rw = rw.toInt()
            keluarga.desa = desa.toInt()

            AddKeluargaPresenter(this@AddKeluargaActivity).addKeluarga(keluarga)
        }
    }

    override fun onSuccessAddKeluarga(msg: String?)
    {
        toast(msg?:"").show()
        finish()
    }

    override fun onErrorAddKeluarga(msg: String?)
    {
        toast(msg?:"").show()
    }

}