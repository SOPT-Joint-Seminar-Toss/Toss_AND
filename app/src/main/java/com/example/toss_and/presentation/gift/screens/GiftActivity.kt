package com.example.toss_and.presentation.gift.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import com.example.toss_and.databinding.ActivityGiftBinding

class GiftActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGiftBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
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
