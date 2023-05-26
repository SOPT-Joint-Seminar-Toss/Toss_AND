package com.example.toss_and.presentation.gift.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toss_and.data.ServicePool
import com.example.toss_and.data.model.RequestGiftDto
import com.example.toss_and.data.model.ResponseGiftDto
import com.example.toss_and.util.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GiftViewModel : ViewModel() {
    private val _giftResult: MutableLiveData<ResponseGiftDto> = MutableLiveData()
    val giftResult: LiveData<ResponseGiftDto> = _giftResult

    private val soptSrvc = ServicePool.service

    fun sentGift(cardType: String, content: String, productId: Int) {
        soptSrvc.sendGift(
            RequestGiftDto(
                cardType,
                content
            ),
            productId
        ).enqueue(object : Callback<ResponseGiftDto> {
            override fun onResponse(
                call: Call<ResponseGiftDto>,
                response: Response<ResponseGiftDto>
            ) {
                if (response.isSuccessful) {
                    _giftResult.value = response.body()
                    Log.d("GiftViewModel", "서버통신 성공")
                } else {
                    Log.e("PurchaseViewModel", "서버통신 실패 (40X)")
                }
            }

            override fun onFailure(call: Call<ResponseGiftDto>, t: Throwable) {
                Log.e("PurchaseViewModel", "서버통신 실패 (응답값 X) ${t.message}")
            }

        })
    }
}