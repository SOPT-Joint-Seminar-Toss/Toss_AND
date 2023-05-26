package com.example.toss_and.presentation.tosspay

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentTossPayBinding
import com.example.toss_and.presentation.tosspay.ServicePool.tossPayService
import com.example.toss_and.util.base.BindingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TossPayFragment : BindingFragment<FragmentTossPayBinding>(R.layout.fragment_toss_pay),
    GroupBuyingAdapter.ItemClickListener {

    lateinit var endDate: String
    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            val startTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
            binding.tvEndTime.text = startTime
////            binding.tvEndTime.text = getRemainingTime(startTime, startTime)
            Log.d("토스 페이 현재 시간 카운트", startTime)
            handler.postDelayed(this, 1000) //1000ms = 1초 간격으로 업데이트
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateTimeRunnable)
        Log.d("현재 시간 핸들러 삭제", "성공~")
    }

    private fun updateTime() {
        handler.removeCallbacks(updateTimeRunnable)
        handler.post(updateTimeRunnable)
    }

    private fun initView() {
        initGroupBuyingRecycler()
        initBrandconRecycler()
        updateTime()
    }

    private fun initGroupBuyingRecycler() {

        tossPayService.getProduct().enqueue(object : Callback<ResponseGroupBuyingDto> {
            override fun onResponse(
                call: Call<ResponseGroupBuyingDto>,
                response: Response<ResponseGroupBuyingDto>
            ) {
                if (response.isSuccessful) {
                    Log.d("유저 프래그먼트 정보 조회 - groupBuying", response.toString())

                    val groupBuyingAdapter = GroupBuyingAdapter(this@TossPayFragment)
                    binding.rvGroupBuying.adapter = groupBuyingAdapter
                    val responseProductList = response.body()?.data
                    val viewModel by viewModels<GroupBuyingViewModel>()
                    groupBuyingAdapter.setProductList(
                        responseProductList!!,
                        viewModel.mockProductList
                    )
//                    groupBuyingAdapter.setOnItemClickListener(object:GroupBuyingAdapter.OnItemClickListener{
//                        override fun onItemClick(position:Int){
//                            endDate = responseProductList[position].endDate!!
//                            Log.d("토스 페이 endDate 변경", endDate)
//                        }
//                    })

//                    var endTime = responseProductList[0].endDate
//                    var startTime = LocalDateTime.now()
//                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
//                    binding.tvTextViewEndTime.text = getRemainingTime(startTime,endTime!!)

                } else {
                    Log.e("유저 프래그먼트 에러 - groupBuying", response.toString())
                }
            }
            override fun onFailure(call: Call<ResponseGroupBuyingDto>, t: Throwable) {
                Log.e("유저 프래그먼트 에러 - groupBuying", "서버 통신 장애")
            }
        })
    }

    private fun initBrandconRecycler() {
        val brandconAdapter = BrandconAdapter()
        binding.rvBrandcon.adapter = brandconAdapter
        tossPayService.getBrand().enqueue(object : Callback<ResponseBrandconDto> {
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

    fun getRemainingTime(date1: String, date2: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val startDate = format.parse(date1)
        val endDate = format.parse(date2)

        if (startDate != null && endDate != null) {
            val diff = endDate.time - startDate.time

            val days = diff / (24 * 60 * 60 * 1000)
            val hours = (diff % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
            val minutes = (diff % (60 * 60 * 1000)) / (60 * 1000)
            val seconds = (diff % (60 * 1000)) / 1000

            return "%02d일 %02d:%02d:%02d".format(days, hours, minutes, seconds)
        } else {
            return "Invalid Dates"
        }
    }

    override fun onItemClick(position: Int) {
        Log.d("토스 페이 클릭 포지션", position.toString())
        Toast.makeText(requireContext(), "Item clicked at position $position", Toast.LENGTH_SHORT)
            .show()
    }
}