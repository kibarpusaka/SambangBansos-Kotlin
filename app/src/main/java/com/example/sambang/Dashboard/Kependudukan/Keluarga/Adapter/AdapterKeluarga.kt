//package com.example.sambang.Dashboard.Kependudukan.Keluarga
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.sambang.Dashboard.Kependudukan.Keluarga.Data.ModelKeluarga
//import com.example.sambang.R
//import kotlinx.android.synthetic.main.list_keluarga_kependudukan.view.*
//
//class AdapterKeluarga(val keluargaD: ArrayList<ModelKeluarga>): RecyclerView.Adapter<AdapterKeluarga.ViewHolder>() {
//
//    var filterDataItem: List<ModelKeluarga>? = keluargaD
//
//    init {
//        filterDataItem = keluargaD
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
//        LayoutInflater.from(parent.context).inflate(R.layout.list_keluarga_kependudukan, parent, false)
//            )
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val keluarga = keluargaD[position]
//        holder.view.tv_nokk_keluarga_kependudukan.text = keluarga.nomerkk.toString()
//        holder.view.tv_alamat_keluarga_kependudukan.text = keluarga.alamat.toString()
//        holder.view.tv_rt_keluarga_kependudukan.text = keluarga.rt.toString()
//        holder.view.tv_rw_keluarga_kependudukan.text = keluarga.rw.toString()
//        holder.view.tv_desa_keluarga_kependudukan.text = keluarga.desa.toString()
//    }
//
//    override fun getItemCount() = keluargaD.size
//
//    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
//
//    fun setDataKeluarga(data: List<ModelKeluarga>){
//        keluargaD.clear()
//        keluargaD.addAll(data)
//        notifyDataSetChanged()
//    }
//}