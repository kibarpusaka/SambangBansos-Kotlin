package com.example.sambang.Dashboard.Kependudukan.NikAktif.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_nik_aktif.view.*

class AdapterNikAktif(val nikAktif: List<ModelNikAktif?>?) : RecyclerView.Adapter<AdapterNikAktif.MyHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_nik_aktif, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = nikAktif?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(nikAktif?.get(position))

//        holder.itemView.ivMenuNikAktif.setOnClickListener {
//            val popupMenu = PopupMenu(holder.itemView.context, it)
//            popupMenu.menuInflater.inflate(R.menu.menu_nik, popupMenu.menu)
//            popupMenu.show()
//
//            popupMenu.setOnMenuItemClickListener {
//                onMenuClicked.click(it, nikAktif?.get(position))
//                true
//            }
//        }
    }

//    interface OnMenuClicked
//    {
//        fun click(menuItem: MenuItem, nikAktif: ModelNikAktif?)
//    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(nikAktif: ModelNikAktif?)
        {
            itemView.tv_nik_aktif.text = nikAktif?.nik
            itemView.tv_nama_nik_aktif.text = nikAktif?.nama
            itemView.tv_tempat_nik_aktif.text = nikAktif?.tempatlahir
            itemView.tv_tanggal_nik_aktif.text = nikAktif?.tanggallahir
            itemView.tv_alamat_nik_aktif.text = nikAktif?.alamat
            itemView.tv_rt_nik_aktif.text = nikAktif?.rt.toString()
            itemView.tv_rw_nik_aktif.text = nikAktif?.rw.toString()
            itemView.tv_nikvalid_nik_aktif.text = nikAktif?.nikvalid.toString()
            itemView.tv_desa_nik_aktif.text = nikAktif?.namaDesa
            itemView.tv_keluarga_nik_aktif.text = nikAktif?.noKK

        }
    }

}