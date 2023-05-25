package com.example.toss_and.presentation.main.home

import android.animation.ValueAnimator
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentHomeBinding
import com.example.toss_and.presentation.MainViewModel
import com.example.toss_and.util.RvDecoration
import com.example.toss_and.util.SampleData
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor
import java.lang.Math.abs

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

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            //val scrollY = binding.scrollView.scrollY // 스크롤 뷰가 스크롤된 정도, pixel

            val loc = IntArray(2)
            binding.clSetting.getLocationOnScreen(loc)
            val triggerPoint = loc[1]
            //- mainVm.tempConsumeHeight

            /*
            val metrics = requireContext().resources.displayMetrics
            val ydp = scrollY / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

            Log.e("ABC", "$scrollY, ${triggerPoint}, $ydp, ${mainVm.tempConsume}")

            val rect = Rect()
            binding.clSetting.getGlobalVisibleRect(rect)
            val r = rect.top

            val tmp = IntArray(2)
            binding.clSetting.getLocationOnScreen(tmp)

            val tmp2 = IntArray(2)
            binding.scrollView.getLocationOnScreen(tmp2)

            Log.d("ABC", "1. ${binding.clSetting.y}")
            Log.d("ABC", "2. ${r}")
            Log.d("ABC", "3. ${binding.clSetting.top}")
            Log.d("ABC", "4. ${tmp[1]}")
            Log.d("ABC", "5. ${binding.scrollView.top}")
            Log.d("ABC", "6. ${tmp2[1]}")
            */

            if (triggerPoint > mainVm.tempConsume) { // 상단에 있다 : 내려가는 중
                mainVm.checkConsumeFlag(true)
            }
            else { // 하단에 있다
                mainVm.checkConsumeFlag(false)
            }
        }

        return binding.root
    }

    private fun ScrollView.computeDistanceToView(view: View): Int {
        return abs(calculateRectOnScreen(this).top - (this.scrollY + calculateRectOnScreen(view).top))
    }

    private fun calculateRectOnScreen(view: View): Rect {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Rect(
            location[0],
            location[1],
            location[0] + view.measuredWidth,
            location[1] + view.measuredHeight
        )
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