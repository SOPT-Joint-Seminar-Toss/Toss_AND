package com.example.toss_and.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentHomeBinding
import com.example.toss_and.util.SampleData
import com.example.toss_and.util.RvDecoration
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor

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
        myAdapter.submitList(SampleData.homeBtmCards)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(requireActivity(), R.color.grey_100)
    }
}