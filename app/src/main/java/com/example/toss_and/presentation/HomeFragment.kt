package com.example.toss_and.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentHomeBinding
import com.example.toss_and.util.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var myAdapter: HomeBtmCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        myAdapter = HomeBtmCardsAdapter()
        binding.rvBottomCards.adapter = myAdapter
        binding.rvBottomCards.addItemDecoration(RvDecoration(30))
        myAdapter.submitList(Constants.homeBtmCards)
        return binding.root
    }

}