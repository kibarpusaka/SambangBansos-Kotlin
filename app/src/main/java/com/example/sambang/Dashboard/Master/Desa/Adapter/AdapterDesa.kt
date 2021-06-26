package com.example.sambang.Dashboard.Master.Desa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Master.Desa.Model.ModelDesaMaster
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_desa_master.view.*
import java.util.ArrayList

class AdapterDesa(val results : ArrayList<ModelDesaMaster>)
    : RecyclerView.Adapter<AdapterDesa.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.list_desa_master, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val desa = results[position]
        holder.view.tv_desa_master.text = desa.nama.toString()
        holder.view.tv_desa_kode_master.text = desa.kode.toString()
        holder.view.tv_desa_kode_kecamatan.text = desa.kecamatan.toString()
    }

    override fun getItemCount() = results.size

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)


    fun setData(data: List<ModelDesaMaster>) {
        results.clear()
        results.addAll( data )
        notifyDataSetChanged()
    }



}