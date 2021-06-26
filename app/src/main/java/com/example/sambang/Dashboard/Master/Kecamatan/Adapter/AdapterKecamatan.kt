package com.example.sambang.Dashboard.Master.Kecamatan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_kecamatan_master.view.*

class AdapterKecamatan(val kecamatanD: ArrayList<ModelKecamatanMaster>): RecyclerView.Adapter<AdapterKecamatan.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.list_kecamatan_master, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kecamatan = kecamatanD[position]
        holder.view.tv_kecamatan_master.text = kecamatan.nama
        holder.view.tv_kode_kecamatan_master.text = kecamatan.kode.toString()
    }

    override fun getItemCount() = kecamatanD.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setDataKecamatan(data: List<ModelKecamatanMaster>){
        kecamatanD.clear()
        kecamatanD.addAll(data)
        notifyDataSetChanged()

    }
}