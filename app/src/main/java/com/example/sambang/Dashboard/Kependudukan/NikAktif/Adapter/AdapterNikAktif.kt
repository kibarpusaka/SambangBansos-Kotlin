package com.example.sambang.Dashboard.Kependudukan.NikAktif.Adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.Dashboard.Kependudukan.NikAktif.Data.ModelNikAktif
import com.example.sambang.R
import kotlinx.android.synthetic.main.list_nik_aktif.view.*

class AdapterNikAktif(val nikAktif: List<ModelNikAktif>, val onMenuClicked: OnMenuClicked) : RecyclerView.Adapter<AdapterNikAktif.MyHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder
    {
        val vn = LayoutInflater.from(parent.context).inflate(R.layout.list_nik_aktif, parent, false)
        return MyHolder(vn)
    }

    override fun getItemCount(): Int = nikAktif.size ?:0

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder.bind(nikAktif.get(position))

        holder.itemView.ivMenuNikAktif.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_nik, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                onMenuClicked.click(it, nikAktif.get(position))
                true
            }
        }
    }

    interface OnMenuClicked
    {
        fun click(menuItem: MenuItem, nikAktif: ModelNikAktif)
    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(nikAktif: ModelNikAktif)
        {

        }
    }

}