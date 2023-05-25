package com.example.toss_and.presentation.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_and.databinding.ItemHomeAssetBinding

class HomeAssetsAdapter :
    ListAdapter<HomeAssetRvDto, RecyclerView.ViewHolder>(HomeAssetsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeAssetsViewHolder(
            ItemHomeAssetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        with((holder as HomeAssetsViewHolder).binding) {
            ivIcon.setImageResource(item.img)
            tvTitle.text = item.title
            tvContent.text = item.content
            if (!item.btn) btnSend.visibility = View.INVISIBLE
        }
    }

    class HomeAssetsViewHolder(val binding: ItemHomeAssetBinding) :
        RecyclerView.ViewHolder(binding.root)
}

object HomeAssetsDiffCallback : DiffUtil.ItemCallback<HomeAssetRvDto>() {
    override fun areItemsTheSame(oldItem: HomeAssetRvDto, newItem: HomeAssetRvDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HomeAssetRvDto, newItem: HomeAssetRvDto): Boolean {
        return oldItem.id == newItem.id
    }
}

