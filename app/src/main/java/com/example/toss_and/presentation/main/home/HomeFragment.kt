package com.example.toss_and.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentHomeBinding
import com.example.toss_and.util.RvDecoration
import com.example.toss_and.util.SampleData
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
        setDefaultListComponents()
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

    private fun setDefaultListComponents() {
        with(binding.clAsset) {
            tvLabel.text = "자산"
            rvAsset.visibility = View.VISIBLE
        }
        with(binding.clInvest) {
            tvLabel.text = "투자"
            clList.clHomeAsset.visibility = View.VISIBLE
            with(clList) {
                ivIcon.setImageResource(R.drawable.icn_spent)
                tvTitle.text = "입출금통장"
                tvContent.text = "잔액 보기"
                btnSend.visibility = View.GONE
                btnNext.visibility = View.VISIBLE
            }
        }
        with(binding.clConsume) {
            tvLabel.text = "소비"
            clList.clHomeAsset.visibility = View.VISIBLE
            with(clList) {
                ivIcon.setImageResource(R.drawable.icn_cards)
                tvTitle.text = "이번 달 쓴 금액"
                tvContent.text = "123,456원"
                btnSend.text = "내역"
            }
        }
    }
}