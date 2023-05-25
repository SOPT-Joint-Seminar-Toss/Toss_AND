package com.example.toss_and.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toss_and.R
import com.example.toss_and.data.ServicePool
import com.example.toss_and.data.model.ResAssetDto
import com.example.toss_and.presentation.main.home.HomeAssetRvDto
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _assetResult: MutableLiveData<List<HomeAssetRvDto>> = MutableLiveData()
    val assetResult: LiveData<List<HomeAssetRvDto>> = _assetResult

    private val soptSrvc = ServicePool.service

    private val _consumeFlag: MutableLiveData<Boolean> = MutableLiveData(true)
    val consumeFlag: LiveData<Boolean> = _consumeFlag
    var tempConsume: Int = 0 // MainActivity에서 clConsume이 위치한 y좌표

    // [서버통신] MainActivity : 자산 목록을 가져온다
    fun getAssets() {
        soptSrvc.getAssets()
            .enqueue(object : retrofit2.Callback<ResAssetDto> {

                override fun onResponse(call: Call<ResAssetDto>, response: Response<ResAssetDto>) {
                    if (response.isSuccessful) {
                        var result = mutableListOf<HomeAssetRvDto>()
                        response.body()?.data?.forEach {
                            result.add(
                                HomeAssetRvDto(
                                    it.id,
                                    assignIcon(it.title),
                                    it.title,
                                    it.balance.toString() + "원",
                                    true
                                )
                            )
                        }
                        result = addAsset(result)
                        Log.d("ABC", result.toString())
                        _assetResult.value = result
                    } else { // 서버통신 실패 (40X)
                        Log.e("ABC", "서버통신 실패 (40X)")
                    }
                }

                override fun onFailure(call: Call<ResAssetDto>, t: Throwable) { // 서버통신 실패 (응답값 X)
                    Log.e("ABC", "서버통신 실패 (응답값 X)")
                }

            })
    }

    // [inverter] _consumeFlag가 (반대값이 들어왔을 때만) 반대값으로 바뀐다
    fun checkConsumeFlag(b: Boolean) {
        if (_consumeFlag.value != b) _consumeFlag.value = b
    }

    private fun addAsset(result: MutableList<HomeAssetRvDto>): MutableList<HomeAssetRvDto> {
        result.add(
            HomeAssetRvDto(
                -1, R.drawable.icn_toss_and_naver, "입출금통장",
                "잔액 보기", false
            )
        )
        result.add(
            HomeAssetRvDto(
                -2, R.drawable.icn_moneybag, "대출. 59개 금융사 대기중",
                "내 최대 대출 한도 보기", false
            )
        )
        return result
    }

    private fun assignIcon(title: String): Int {
        return when (title) {
            "토스뱅크 통장" -> R.drawable.icn_toss_logo
            "토스뱅크 돈 모으기" -> R.drawable.icn_accumulate
            "KB 국민은행 통장" -> R.drawable.icn_kb
            else -> 0
        }
    }
}