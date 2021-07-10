package com.example.sambang.Dashboard.Master.Kecamatan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Master.Kecamatan.Data.ModelKecamatanMaster
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_kecamatan_master.view.*

class AdapterKecamatan(val kecamatan: List<ModelKecamatanMaster?>?) : RecyclerView.Adapter<AdapterKecamatan.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_kecamatan_master, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() : Int = kecamatan?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kecamatan?.get(position))
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(kecamatan: ModelKecamatanMaster?){
            itemView.tv_kecamatan_master.text = kecamatan?.nama
            itemView.tv_kode_kecamatan_master.text = kecamatan?.kode.toString()
        }
    }

}