package com.example.toss_and.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_and.databinding.ItemHomeBottomCardsBinding

class HomeBtmCardsAdapter
    : ListAdapter<HomeBtmCardsDto, RecyclerView.ViewHolder>(HomeBottomCardsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeBtmCardsViewHolder(
            ItemHomeBottomCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        with((holder as HomeBtmCardsViewHolder).binding) {
            tvSubtitle.text = item.subtitle
            tvTitle.text = item.title
            ivIcon.setImageResource(item.img)
        }
    }

    class HomeBtmCardsViewHolder(val binding: ItemHomeBottomCardsBinding) :
            RecyclerView.ViewHolder(binding.root)
}

object HomeBottomCardsDiffCallback : DiffUtil.ItemCallback<HomeBtmCardsDto>() {
    override fun areItemsTheSame(
        oldItem: HomeBtmCardsDto,
        newItem: HomeBtmCardsDto
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: HomeBtmCardsDto,
        newItem: HomeBtmCardsDto
    ): Boolean {
        return oldItem == newItem
    }
}