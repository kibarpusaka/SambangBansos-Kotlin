package com.example.sambang.Dashboard.Kependudukan.NikNonAktif.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_nik_non_aktif.view.*

class AdapterNikNonAktif(val nikNonAktif: List<ModelNikAktif?>?) : RecyclerView.Adapter<AdapterNikNonAktif.MyHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_nik_non_aktif, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = nikNonAktif?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(nikNonAktif?.get(position))

    }


    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(nikNonAktif: ModelNikAktif?)
        {
            itemView.tv_nik_non_aktif.text = nikNonAktif?.nik
            itemView.tv_nama_nik_non_aktif.text = nikNonAktif?.nama
            itemView.tv_tempat_nik_non_aktif.text = nikNonAktif?.tempatlahir
            itemView.tv_tanggal_nik_non_aktif.text = nikNonAktif?.tanggallahir
            itemView.tv_alamat_nik_non_aktif.text = nikNonAktif?.alamat
            itemView.tv_rt_nik_non_aktif.text = nikNonAktif?.rt.toString()
            itemView.tv_rw_nik_non_aktif.text = nikNonAktif?.rw.toString()
            itemView.tv_nikvalid_nik_non_aktif.text = nikNonAktif?.nikvalid.toString()
            itemView.tv_desa_nik_non_aktif.text = nikNonAktif?.desa.toString()
            itemView.tv_keluarga_nik_non_aktif.text = nikNonAktif?.keluarga.toString()

        }
    }
}