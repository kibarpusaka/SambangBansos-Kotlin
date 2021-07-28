package com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Adapter.KeluargaAdapter
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_keluarga_kependudukan.view.*
import kotlinx.android.synthetic.main.list_penerima_bantuan.view.*

class AdapterPenerimaBantuan(val penerimaBantuan: List<ModelPenerimaBantuan?>?, val onMenuClicked: OnMenuClicked) : RecyclerView.Adapter<AdapterPenerimaBantuan.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_penerima_bantuan, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() : Int = penerimaBantuan?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(penerimaBantuan?.get(position))

        holder.itemView.ivMenuPenerimaBantuan.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_penerima_bantuan, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                onMenuClicked.click(it, penerimaBantuan?.get(position)!!)
                true
            }
        }
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(penerimaBantuan: ModelPenerimaBantuan?){
            itemView.tv_status_penerima_bantuan.text = penerimaBantuan?.status.toString()
            itemView.tv_tanggal_penerima_bantuan.text = penerimaBantuan?.tglpengajuan
            itemView.tv_bantuan_penerima_bantuan.text = penerimaBantuan?.namaBantuan
            itemView.tv_keluarga_penerima_bantuan.text = penerimaBantuan?.keluarga.toString()

        }
    }

    interface OnMenuClicked
    {
        fun click(menuItem: MenuItem, penerimaBantuan: ModelPenerimaBantuan)
    }

}