package com.example.sambang.Dashboard.Master.Desa.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_desa_master.view.*

class AdapterDesa(val desa: List<ModelDesaMaster?>?) : RecyclerView.Adapter<AdapterDesa.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_desa_master, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() : Int = desa?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(desa?.get(position))
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(desa: ModelDesaMaster?){
            itemView.tv_desa_master.text = desa?.nama
            itemView.tv_desa_kode_master.text = desa?.kode.toString()
            itemView.tv_desa_kode_kecamatan.text = desa?.kecamatan.toString()
        }
    }

}