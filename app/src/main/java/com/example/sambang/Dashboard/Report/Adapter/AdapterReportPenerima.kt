package com.example.sambang.Dashboard.Report.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.*

class AdapterReportPenerima(val penerimaBantuan: List<ModelPenerimaBantuan?>?) : RecyclerView.Adapter<AdapterReportPenerima.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_penerima_bantuan, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() : Int = penerimaBantuan?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(penerimaBantuan?.get(position))
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(penerimaBantuan: ModelPenerimaBantuan?){
            itemView.tv_status_penerima_bantuan.text = penerimaBantuan?.status.toString()
            itemView.tv_tanggal_penerima_bantuan.text = penerimaBantuan?.tglpengajuan
            itemView.tv_bantuan_penerima_bantuan.text = penerimaBantuan?.bantuan.toString()
            itemView.tv_keluarga_penerima_bantuan.text = penerimaBantuan?.keluarga.toString()

        }
    }

}