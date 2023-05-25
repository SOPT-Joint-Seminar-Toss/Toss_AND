package com.example.toss_and.presentation.purchase.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toss_and.data.ServicePool
import com.example.toss_and.data.model.BrandconDto
import retrofit2.Call
import retrofit2.Response

class PurchaseViewModel : ViewModel() {
    private val _brandconResult: MutableLiveData<BrandconDto> = MutableLiveData()
    val brandconResult: LiveData<BrandconDto> = _brandconResult

    private val soptSrvc = ServicePool.service

    fun getBrandconDetail(productId : Int) {
        soptSrvc.getBrandconDetail(productId)
            .enqueue(object : retrofit2.Callback<BrandconDto> {
                override fun onResponse(call: Call<BrandconDto>, response: Response<BrandconDto>) {
                    if (response.isSuccessful) {
                        _brandconResult.value = response.body()
                        Log.d("PurchaseViewModel", "서버통신 성공")
                    } else {
                        Log.e("PurchaseViewModel", "서버통신 실패 (40X)")
                    }
                }

                override fun onFailure(call: Call<BrandconDto>, t: Throwable) {
                    Log.e("PurchaseViewModel", "서버통신 실패 (응답값 X) ${t.message}")
                }

            })
    }
}