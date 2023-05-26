package com.example.toss_and.presentation.tosspay

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.toss_and.databinding.ItemBrandconBinding
import java.text.DecimalFormat

class BrandconViewHolder(private val binding: ItemBrandconBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ResponseBrandconDto.Data) {
        val dec = DecimalFormat("#,###")
        val price = dec.format(item.price).toString()
        binding.ivBrandcon.load(item.imageUrl)
        binding.tvBranconPrice.text = String.format("%S원", price)
        binding.tvBranconDetail.text = String.format("%s | %s", item.brandTitle, item.productTitle)
        binding.tvBranconPoint.text = String.format("%S원 적립", item.point)
    }
}