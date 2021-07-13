package com.example.sambang.ui.beneficiary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.sambang.R
import com.example.sambang.databinding.ListPenerimaBantuanBinding
import com.example.sambang.domain.beneficiary.entity.BeneficiaryEntity

class AdapterPenerimaBantuan(
    private val beneficiaries: MutableList<BeneficiaryEntity>
) : RecyclerView.Adapter<AdapterPenerimaBantuan.ViewHolder>() {

    interface AdapterListener {
        fun onTapEdit(beneficiary: BeneficiaryEntity)
        fun onTapDelete(beneficiary: BeneficiaryEntity)
    }

    private var adapterListener : AdapterListener? = null

    fun setAdapterListener(l: AdapterListener){
        adapterListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v = ListPenerimaBantuanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    fun update(list: List<BeneficiaryEntity>){
        beneficiaries.clear()
        beneficiaries.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int = beneficiaries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(beneficiaries[position])

    inner class ViewHolder (private val itemBinding: ListPenerimaBantuanBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(b: BeneficiaryEntity){
            itemBinding.tvStatusPenerimaBantuan.text = b.status.toString()
            itemBinding.tvTanggalPenerimaBantuan.text = b.dateProposed
            itemBinding.tvBantuanPenerimaBantuan.text = b.support.toString()
            itemBinding.tvKeluargaPenerimaBantuan.text = b.familyId.toString()
            itemBinding.ivMenuPenerimaBantuan.setOnClickListener {
                val popupMenu = PopupMenu(itemBinding.root.context, it)
                popupMenu.menuInflater.inflate(R.menu.menu_penerima_bantuan, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId){
                        R.id.editPenerimaBantuan -> {
                            adapterListener?.onTapEdit(b)
                            return@setOnMenuItemClickListener true
                        }
                        R.id.hapusPenerimaBantuan -> {
                            adapterListener?.onTapDelete(b)
                            return@setOnMenuItemClickListener true
                        }
                        else -> return@setOnMenuItemClickListener false
                    }
                }
                popupMenu.show()
            }

        }
    }
}