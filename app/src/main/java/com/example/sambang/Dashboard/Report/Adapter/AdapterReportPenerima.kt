package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.*
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_alamat_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_desa_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_keluarga_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_nama_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_nik_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_nikvalid_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_rt_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_rw_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_status_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_tanggal_daftar_usulan
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_tempat_daftar_usulan
import kotlinx.android.synthetic.main.list_report.view.*

class AdapterReportPenerima(val penerimaBantuan: List<ModelNikAktif?>?) : RecyclerView.Adapter<AdapterReportPenerima.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_report, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = penerimaBantuan?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(penerimaBantuan?.get(position))

    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(daftarUsulan: ModelNikAktif?)
        {
            itemView.tv_nik_daftar_usulan.text = daftarUsulan?.nik
            itemView.tv_nama_daftar_usulan.text = daftarUsulan?.nama
            itemView.tv_tempat_daftar_usulan.text = daftarUsulan?.tempatlahir
            itemView.tv_tanggal_daftar_usulan.text = daftarUsulan?.tanggallahir
            itemView.tv_alamat_daftar_usulan.text = daftarUsulan?.alamat
            itemView.tv_rt_daftar_usulan.text = daftarUsulan?.rt.toString()
            itemView.tv_rw_daftar_usulan.text = daftarUsulan?.rw.toString()
            itemView.tv_status_daftar_usulan.text = daftarUsulan?.status.toString()
            itemView.tv_nikvalid_daftar_usulan.text = daftarUsulan?.nikvalid.toString()
            itemView.tv_keluarga_daftar_usulan.text = daftarUsulan?.noKK
            itemView.tv_bantuan_daftar_usulan.text = daftarUsulan?.namaBantuan

            itemView.tv_desa_daftar_usulan.text = daftarUsulan?.namaDesa
        }
    }

}