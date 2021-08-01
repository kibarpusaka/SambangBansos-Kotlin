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
//import kotlinx.android.synthetic.main.list_penerima_bantuan.view.tv_bantuan_daftar_usulan
import kotlinx.android.synthetic.main.list_report.view.*

class AdapterPenerimaBantuan(val penerimaBantuan: List<ModelNikAktif?>?, val onMenuClicked : AdapterPenerimaBantuan.OnMenuClicked) : RecyclerView.Adapter<AdapterPenerimaBantuan.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_penerima_bantuan, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = penerimaBantuan?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(penerimaBantuan?.get(position))

        holder.itemView.ivMenuPenerima.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_penerima_bantuan, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                onMenuClicked.click(it, penerimaBantuan?.get(position)!!)
                true
            }
        }

    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(penerimaBantuan: ModelNikAktif?)
        {
            itemView.tv_nik_daftar_usulan.text = penerimaBantuan?.nik
            itemView.tv_nama_daftar_usulan.text = penerimaBantuan?.nama
            itemView.tv_tempat_daftar_usulan.text = penerimaBantuan?.tempatlahir
            itemView.tv_tanggal_daftar_usulan.text = penerimaBantuan?.tanggallahir
            itemView.tv_alamat_daftar_usulan.text = penerimaBantuan?.alamat
            itemView.tv_rt_daftar_usulan.text = penerimaBantuan?.rt.toString()
            itemView.tv_rw_daftar_usulan.text = penerimaBantuan?.rw.toString()
            itemView.tv_status_daftar_usulan.text = penerimaBantuan?.status.toString()
            itemView.tv_nikvalid_daftar_usulan.text = penerimaBantuan?.nikvalid.toString()
            itemView.tv_keluarga_daftar_usulan.text = penerimaBantuan?.noKK
//            itemView.tv_bantuan_daftar_usulan.text = penerimaBantuan?.namaBantuan

            itemView.tv_desa_daftar_usulan.text = penerimaBantuan?.namaDesa
        }
    }

    interface OnMenuClicked {
        fun click(menuItem: MenuItem, penerimaBantuan: ModelNikAktif)
    }

}