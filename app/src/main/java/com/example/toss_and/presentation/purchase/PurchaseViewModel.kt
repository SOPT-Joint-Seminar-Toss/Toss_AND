package com.example.toss_and.presentation.purchase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toss_and.data.ServicePool
import com.example.toss_and.data.model.ResLikeDto
import retrofit2.Call
import retrofit2.Response

class PurchaseViewModel : ViewModel() {
    private val _likeResult: MutableLiveData<Boolean> = MutableLiveData(false)
    val likeResult: LiveData<Boolean> = _likeResult

    private val soptSrvc = ServicePool.service

    fun sendLike(id: Int) {
        soptSrvc.clickLike(productId = id)
            .enqueue(object : retrofit2.Callback<ResLikeDto> {

                override fun onResponse(call: Call<ResLikeDto>, response: Response<ResLikeDto>) {
                    if (response.isSuccessful) {
                        _likeResult.value = true
                    }
                    else Log.e("ABC", "서버통신 실패 (40X)")
                }

                override fun onFailure(call: Call<ResLikeDto>, t: Throwable) {
                    Log.e("ABC", "서버통신 실패 (응답값 X)")
                }
            })
    }
}