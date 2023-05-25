package com.example.toss_and.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toss_and.data.ServicePool
import com.example.toss_and.data.model.ResAssetDto
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _assetResult: MutableLiveData<ResAssetDto> = MutableLiveData()
    val assetResult: LiveData<ResAssetDto> = _assetResult

    private val soptSrvc = ServicePool.service

    private val _consumeFlag: MutableLiveData<Boolean> = MutableLiveData(true)
    val consumeFlag: LiveData<Boolean> = _consumeFlag

    var tempConsume: Int = 0

    fun getAssets() {
        soptSrvc.getAssets()
            .enqueue(object : retrofit2.Callback<ResAssetDto> {

                override fun onResponse(call: Call<ResAssetDto>, response: Response<ResAssetDto>) {
                    if (response.isSuccessful) {
                        _assetResult.value = response.body()
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
}