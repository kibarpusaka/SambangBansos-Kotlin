package com.example.sambang.Dashboard.Master.Bantuan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Master.Bantuan.Data.ModelBantuanMaster
import com.example.sambang.Dashboard.Master.Desa.Data.ModelDesaMaster
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_bantuan_master.view.*
import kotlinx.android.synthetic.main.list_desa_master.view.*

class AdapterBantuanMaster(val bantuan: List<ModelBantuanMaster?>?) : RecyclerView.Adapter<AdapterBantuanMaster.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_bantuan_master, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() : Int = bantuan?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bantuan?.get(position))
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bantuan: ModelBantuanMaster?){
            itemView.tv_nama_bantuan_master.text = bantuan?.nama
            itemView.tv_tahun_bantuan_master.text = bantuan?.tahun.toString()
            itemView.tv_sumber_bantuan_master.text = bantuan?.sumber
        }
    }

}
