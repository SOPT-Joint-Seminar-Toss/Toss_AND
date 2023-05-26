package com.example.toss_and.presentation.tosspay

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.toss_and.databinding.ItemGroupBuyingBinding
import java.text.DecimalFormat

class GroupBuyingViewHolder(
    private val binding: ItemGroupBuyingBinding,
    itemClickListener: GroupBuyingAdapter.ItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            itemClickListener.onItemClick(adapterPosition)
        }
    }

    fun onBind(item: ResponseGroupBuyingDto.Data, mockItem: RecycleGroupBuyingData) =
        with(binding) {

            val dec = DecimalFormat("#,###")
            val price = dec.format(item.price).toString()
            ivProduct.load(item.imageUrl)
            tvProductTitle.text = item.title
            tvDiscountRate.text = String.format("%s%%", item.discountRate)
            tvProductPrice.text = String.format("%s원", price)
            ivProfiles.setImageResource(mockItem.imgId)
            tvPeople.text = mockItem.people
        }
}