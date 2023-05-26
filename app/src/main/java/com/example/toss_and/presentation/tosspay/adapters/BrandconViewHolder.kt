package com.example.toss_and.presentation.tosspay.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.toss_and.data.model.ResponseBrandconDto
import com.example.toss_and.databinding.ItemBrandconBinding
import java.text.DecimalFormat

class BrandconViewHolder(
    private val binding: ItemBrandconBinding,
    itemClickListener: BrandconAdapter.ItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            itemClickListener.onItemClick(adapterPosition)
        }
    }

    fun onBind(item: ResponseBrandconDto.Data) {
        val dec = DecimalFormat("#,###")
        val price = dec.format(item.price).toString()
        binding.ivBrandcon.load(item.imageUrl)
        binding.tvBranconPrice.text = String.format("%S원", price)
        binding.tvBranconDetail.text = String.format("%s | %s", item.brandTitle, item.productTitle)
        binding.tvBranconPoint.text = String.format("%S원 적립", item.point)
    }
}