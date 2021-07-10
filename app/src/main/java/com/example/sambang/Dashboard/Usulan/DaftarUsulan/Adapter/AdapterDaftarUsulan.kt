package com.example.sambang.Dashboard.Usulan.DaftarUsulan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Usulan.DaftarUsulan.Data.ModelDaftarUsulan
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_daftar_usulan.view.*

class AdapterDaftarUsulan(val daftarUsulan: List<ModelDaftarUsulan?>?) : RecyclerView.Adapter<AdapterDaftarUsulan.MyHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_daftar_usulan, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = daftarUsulan?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(daftarUsulan?.get(position))

    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(daftarUsulan: ModelDaftarUsulan?)
        {
            itemView.tv_nik_daftar_usulan.text = daftarUsulan?.nik
            itemView.tv_nama_daftar_usulan.text = daftarUsulan?.nama
            itemView.tv_tempat_daftar_usulan.text = daftarUsulan?.tempatlahir
            itemView.tv_tanggal_daftar_usulan.text = daftarUsulan?.tanggallahir
            itemView.tv_alamat_daftar_usulan.text = daftarUsulan?.alamat
            itemView.tv_rt_daftar_usulan.text = daftarUsulan?.rt.toString()
            itemView.tv_rw_daftar_usulan.text = daftarUsulan?.rw.toString()
            itemView.tv_nikvalid_daftar_usulan.text = daftarUsulan?.nikvalid.toString()
            itemView.tv_desa_daftar_usulan.text = daftarUsulan?.desa.toString()
            itemView.tv_keluarga_daftar_usulan.text = daftarUsulan?.keluarga.toString()

        }
    }

}
