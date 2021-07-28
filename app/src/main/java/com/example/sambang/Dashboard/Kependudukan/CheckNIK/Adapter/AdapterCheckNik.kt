package com.example.sambang.Dashboard.Kependudukan.CheckNIK.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Adapter.AdapterNikAktif
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_check_nik_kependudukan.view.*
import kotlinx.android.synthetic.main.list_nik_aktif.view.*

class AdapterCheckNik(val checkNik: List<ModelNikAktif?>?, val onMenuClicked: OnMenuClicked) : RecyclerView.Adapter<AdapterCheckNik.MyHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_check_nik_kependudukan, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = checkNik?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(checkNik?.get(position))
        holder.itemView.ivMenuCheckNik.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_nik, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                onMenuClicked.click(it, checkNik?.get(position))
                true
            }
        }

    }
    interface OnMenuClicked
    {
        fun click(menuItem: MenuItem, checkNik: ModelNikAktif?)
    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(checkNik: ModelNikAktif?)
        {
            itemView.tv_nik_checknik.text = checkNik?.nik
            itemView.tv_nama_checknik.text = checkNik?.nama
            itemView.tv_tempat_checknik.text = checkNik?.tempatlahir
            itemView.tv_tanggal_checknik.text = checkNik?.tanggallahir
            itemView.tv_alamat_checknik.text = checkNik?.alamat
            itemView.tv_rt_checknik.text = checkNik?.rt.toString()
            itemView.tv_rw_checknik.text = checkNik?.rw.toString()
            itemView.tv_nikvalid_checknik.text = checkNik?.nikvalid.toString()
            itemView.tv_desa_checknik.text = checkNik?.namaDesa
            itemView.tv_keluarga_checknik.text = checkNik?.noKK

        }
    }

}
