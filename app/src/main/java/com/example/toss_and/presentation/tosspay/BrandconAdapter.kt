package com.example.toss_and.presentation.tosspay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_and.databinding.ItemBrandconBinding
class BrandconAdapter : RecyclerView.Adapter<BrandconViewHolder>() {
    private var itemList: List<ResponseBrandconDto.Data> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandconViewHolder {
        val binding: ItemBrandconBinding =
            ItemBrandconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandconViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: BrandconViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    fun setBrandconList(brandconList: List<ResponseBrandconDto.Data>) {
        this.itemList = brandconList.toList()
        notifyDataSetChanged()
    }
}