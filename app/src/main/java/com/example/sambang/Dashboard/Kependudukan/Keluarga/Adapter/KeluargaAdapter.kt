package com.example.sambang.Dashboard.Kependudukan.Keluarga.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Bantuan.PenerimaBantuan.Data.ModelPenerimaBantuan
import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_keluarga_kependudukan.view.*

class KeluargaAdapter(val keluarga: List<ModelKeluarga?>?, val onMenuClicked: KeluargaAdapter.OnMenuClicked) : RecyclerView.Adapter<KeluargaAdapter.MyHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_keluarga_kependudukan, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int = keluarga?.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(keluarga?.get(position))

        holder.itemView.ivMenuKeluarga.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_keluarga, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                onMenuClicked.click(it, keluarga?.get(position)!!)
                true
            }
        }
    }

    class MyHolder(itemView : View): RecyclerView.ViewHolder(itemView)
    {
        fun bind(keluarga: ModelKeluarga?)
        {
            itemView.tv_id_keluarga_kependudukan.text = keluarga?.id.toString()
            itemView.tv_nokk_keluarga_kependudukan.text = keluarga?.nomerkk.toString()
            itemView.tv_alamat_keluarga_kependudukan.text = keluarga?.alamat
            itemView.tv_rt_keluarga_kependudukan.text = keluarga?.rt.toString()
            itemView.tv_rw_keluarga_kependudukan.text = keluarga?.rw.toString()
            itemView.tv_desa_keluarga_kependudukan.text = keluarga?.namaDesa
        }
    }

    interface OnMenuClicked
    {
        fun click(menuItem: MenuItem, keluarga: ModelKeluarga)
    }
}