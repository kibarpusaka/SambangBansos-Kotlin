package com.example.sambang.ui.list_of_propsal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.databinding.ListDaftarUsulanBinding
import com.example.sambang.domain.proposal.entity.ResidentInformationEntity

class AdapterDaftarUsulan(private val proposals: MutableList<ResidentInformationEntity>) : RecyclerView.Adapter<AdapterDaftarUsulan.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = ListDaftarUsulanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(v)
    }

    fun update(list: List<ResidentInformationEntity>){
        proposals.clear()
        proposals.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = proposals.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(proposals[position])

    inner class MyHolder(private val itemBinding: ListDaftarUsulanBinding) : RecyclerView.ViewHolder(itemBinding.root)
    {
        fun bind(p: ResidentInformationEntity) {
            itemBinding.tvNikDaftarUsulan.text = p.nik
            itemBinding.tvNamaDaftarUsulan.text = p.name
            itemBinding.tvTempatDaftarUsulan.text = p.placeOfBirth
            itemBinding.tvTanggalDaftarUsulan.text = p.dateOfBirth
            itemBinding.tvAlamatDaftarUsulan.text = p.address
            itemBinding.tvRtDaftarUsulan.text = p.rt.toString()
            itemBinding.tvRwDaftarUsulan.text = p.rw.toString()
            itemBinding.tvNikvalidDaftarUsulan.text = if (p.isNikValid) "VALID" else "TIDAK VALID"
            itemBinding.tvDesaDaftarUsulan.text = p.villageId.toString()
            itemBinding.tvKeluargaDaftarUsulan.text = p.familyId.toString()
        }
    }

}
