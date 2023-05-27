package com.example.toss_and.presentation.tosspay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_and.data.model.ResponseBrandconDto
import com.example.toss_and.databinding.ItemBrandconBinding

class BrandconAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<BrandconViewHolder>() {
    private var itemList: List<ResponseBrandconDto.Data> = emptyList()

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandconViewHolder {
        val binding: ItemBrandconBinding =
            ItemBrandconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandconViewHolder(binding, itemClickListener)
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