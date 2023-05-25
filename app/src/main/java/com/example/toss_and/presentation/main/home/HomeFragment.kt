package com.example.toss_and.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentHomeBinding
import com.example.toss_and.presentation.MainViewModel
import com.example.toss_and.util.RvDecoration
import com.example.toss_and.util.SampleData
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var myAdapter: HomeBtmCardsAdapter
    private val mainVm: MainViewModel by activityViewModels()

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

        registerScrollListener()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(requireActivity(), R.color.grey_100)
    }

    private fun registerScrollListener() {
        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            //val scrollY = binding.scrollView.scrollY // 스크롤 뷰가 스크롤된 정도, pixel

            val loc = IntArray(2)
            binding.clSetting.getLocationOnScreen(loc)
            val triggerPoint = loc[1]

            // 1. trigger point가 상단에 있다 (내려가야 됨)
            if (triggerPoint > mainVm.tempConsume)
                mainVm.checkConsumeFlag(true)
            // 2. trigger point가 하단에 있다 (올라가야 됨)
            else
                mainVm.checkConsumeFlag(false)
        }
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