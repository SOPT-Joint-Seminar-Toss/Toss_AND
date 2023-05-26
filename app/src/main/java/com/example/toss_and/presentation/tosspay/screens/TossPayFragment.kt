package com.example.toss_and.presentation.tosspay.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_and.R
import com.example.toss_and.data.ServicePool.service
import com.example.toss_and.data.model.ResponseBrandconDto
import com.example.toss_and.data.model.ResponseGroupBuyingDto
import com.example.toss_and.databinding.FragmentTossPayBinding
import com.example.toss_and.presentation.purchase.screens.PurchaseActivity
import com.example.toss_and.presentation.tosspay.adapters.GroupBuyingAdapter
import com.example.toss_and.presentation.tosspay.viewmodels.GroupBuyingViewModel
import com.example.toss_and.presentation.tosspay.adapters.BrandconAdapter
import com.example.toss_and.util.base.BindingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TossPayFragment : BindingFragment<FragmentTossPayBinding>(R.layout.fragment_toss_pay),
    BrandconAdapter.ItemClickListener {


    private var endDateTimeArray = arrayOf<String>(getLocalTimeNow(), getLocalTimeNow())
    private var centerPosition = 0
    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            val remainingTime = getRemainingTime(
                getLocalTimeNow(),
                endDateTimeArray[centerPosition]
            )
            binding.tvEndTime.text = remainingTime
            Log.d("토스 페이 남은 시간 카운트", remainingTime)
            handler.postDelayed(this, 1000) //1000ms = 1초 간격으로 업데이트
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateTimeRunnable) // fragment가 destroy 되면 러너블 객체 삭제
    }

    private fun initView() {
        initGroupBuyingRecycler()
        initBrandconRecycler()
        updateTime()
    }

    /** updateTimeRunnable 갱신 */
    private fun updateTime() {
        handler.removeCallbacks(updateTimeRunnable)
        handler.post(updateTimeRunnable)
    }

    /** 최저가 공동구매 recycler view 초기화 */
    private fun initGroupBuyingRecycler() {

        service.getProduct().enqueue(object : Callback<ResponseGroupBuyingDto> {
            override fun onResponse(
                call: Call<ResponseGroupBuyingDto>,
                response: Response<ResponseGroupBuyingDto>
            ) {
                if (response.isSuccessful) {
                    val responseProductList = response.body()?.data
                    val groupBuyingAdapter = GroupBuyingAdapter()
                    binding.rvGroupBuying.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvGroupBuying.adapter = groupBuyingAdapter
                    val viewModel by viewModels<GroupBuyingViewModel>()

                    groupBuyingAdapter.setProductList(
                        responseProductList!!,
                        viewModel.mockProductList
                    )
                    endDateTimeArray =
                        arrayOf(responseProductList[0].endDate!!, responseProductList[1].endDate!!)
                    Log.d("토스 페이 endDate 변경", endDateTimeArray[1])


                    val snapHelper = LinearSnapHelper()
                    snapHelper.attachToRecyclerView(binding.rvGroupBuying)
                    binding.rvGroupBuying.addOnScrollListener(object :
                        RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            val layoutManager = recyclerView.layoutManager
                            val centerView = snapHelper.findSnapView(layoutManager)
                            val position = centerView?.let { layoutManager?.getPosition(it) }
                            if (position != null && position >= 0) {
                                centerPosition = position
                                showCurrentItem(centerPosition)
                            }
                        }
                    })

                } else {
                    Log.e("유저 프래그먼트 에러 - groupBuying", response.toString())
                }
            }

            override fun onFailure(call: Call<ResponseGroupBuyingDto>, t: Throwable) {
                Log.e("유저 프래그먼트 에러 - groupBuying", "서버 통신 장애")
            }
        })
    }

    /** 인기브랜드콘 recycler view 초기화 */
    private fun initBrandconRecycler() {
        val brandconAdapter = BrandconAdapter(this)
        binding.rvBrandcon.adapter = brandconAdapter
        service.getBrand().enqueue(object : Callback<ResponseBrandconDto> {
            override fun onResponse(
                call: Call<ResponseBrandconDto>,
                response: Response<ResponseBrandconDto>
            ) {
                if (response.isSuccessful) {
                    Log.d("유저 프래그먼트 정보 조회 - brandcon", response.toString())
                    val responseBrandconList = response.body()?.data?.slice(0..2)
                    brandconAdapter.setBrandconList(responseBrandconList!!)

                } else {
                    Log.e("유저 프래그먼트 에러 - brandcon", response.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBrandconDto>, t: Throwable) {
                Log.e("유저 프래그먼트 에러 - brandcon", "서버 통신 장애")
            }
        })
    }

    /** 두 날짜를 비교한 뒤 차이를 return */
    fun getRemainingTime(date1: String, date2: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val startDate = format.parse(date1)
        val endDate = format.parse(date2)

        return if (startDate != null && endDate != null) {
            val diff = endDate.time - startDate.time

            val days = diff / (24 * 60 * 60 * 1000)
            val hours = (diff % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
            val minutes = (diff % (60 * 60 * 1000)) / (60 * 1000)
            val seconds = (diff % (60 * 1000)) / 1000

            "%02d일 %02d:%02d:%02d 남음".format(days, hours, minutes, seconds)
        } else {
            "Invalid Dates"
        }
    }

    /** tv_end_time에 남은 시간 갱신 */
    private fun showCurrentItem(position: Int) {
        binding.tvEndTime.text = getRemainingTime(getLocalTimeNow(), endDateTimeArray[position])
    }

    /** 현재 localDateTime을 return */
    private fun getLocalTimeNow(): String {
        return LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
    }

    override fun onItemClick(position: Int) {
        if (position == 0) {
            val intent = Intent(requireContext(), PurchaseActivity::class.java)
            startActivity(intent)
        }
    }
}