package com.example.toss_and.presentation.tosspay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_and.databinding.ItemGroupBuyingBinding

class GroupBuyingAdapter() :
    RecyclerView.Adapter<GroupBuyingViewHolder>() {
    private var itemList: List<ResponseGroupBuyingDto.Data> = emptyList()
    private var mockItemList: List<RecycleGroupBuyingData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupBuyingViewHolder {
        val binding =
            ItemGroupBuyingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupBuyingViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: GroupBuyingViewHolder, position: Int) {
        holder.onBind(itemList[position], mockItemList[position])

        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = 860
        holder.itemView.requestLayout()
    }

    fun setProductList(
        productList: List<ResponseGroupBuyingDto.Data>,
        detailList: List<RecycleGroupBuyingData>
    ) {
        this.itemList = productList.toList()
        this.mockItemList = detailList.toList()
        notifyDataSetChanged()
    }
}