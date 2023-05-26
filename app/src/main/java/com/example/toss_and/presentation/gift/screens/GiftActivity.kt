package com.example.toss_and.presentation.gift.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import com.example.toss_and.databinding.ActivityGiftBinding
import com.example.toss_and.presentation.gift.viewmodels.GiftViewModel
import com.example.toss_and.util.showToast

class GiftActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGiftBinding
    private val giftVm by viewModels<GiftViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        registerObserver()
        editMessage()
        completeMessage()
        observeMessage()
        deleteAllMessage()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun registerObserver() {
        binding.btnComplete.setOnClickListener {
            giftVm.sentGift(
                "BALLOON",
                binding.etMessage.text.toString(),
                1
            )
        }
        giftVm.giftResult.observe(this) {
            if (it.code == 200) {
                Log.d("giftActivity", "200")
                showToast("카드가 저장되었습니다.")
            } else {
                Log.d("giftActivity", "400")
                showToast("카드 저장에 실패했습니다.")
            }
        }
    }

    private fun editMessage() {
        with(binding){
            ivModifyMessageBtn.setOnClickListener {
                modifyVisibility(ivModifyMessageBtn, 0)
                modifyVisibility(tvSelectCard, 0)
                modifyVisibility(ivImageBtn, 0)
                modifyVisibility(tvWordCount, 1)
                modifyVisibility(ivWordDelete, 1)
                modifyVisibility(btnCompleteModify, 1)
                etMessage.requestFocus()
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etMessage, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    private fun completeMessage() {
        with(binding){
            btnCompleteModify.setOnClickListener {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                modifyVisibility(tvSelectCard, 1)
                modifyVisibility(ivImageBtn, 1)
                modifyVisibility(ivModifyMessageBtn, 1)
                modifyVisibility(tvWordCount, 0)
                modifyVisibility(ivWordDelete, 0)
                modifyVisibility(btnComplete, 1)
                modifyVisibility(btnCompleteModify, 0)
                etMessage.clearFocus()
            }
        }
    }

    private fun observeMessage() {
        with(binding) {
            etMessage.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val input = etMessage.text.toString().length
                    if (input > 21) {
                        etMessage.setTextSize(Dimension.SP, 16F)
                    } else {
                        etMessage.setTextSize(Dimension.SP, 22F)
                    }
                    tvWordCount.text = "$input/86자"
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun deleteAllMessage() {
        binding.ivWordDelete.setOnClickListener {
            binding.etMessage.text = null
        }
    }

    private fun modifyVisibility(view: View, type: Int) {
        if (type == VISIBILITY_VISIBLE) {
            view.visibility = View.VISIBLE
        } else if (type == VISIBILITY_GONE){
            view.visibility = View.GONE
        }
    }

    companion object {
        const val VISIBILITY_GONE = 0
        const val VISIBILITY_VISIBLE = 1
    }
}
