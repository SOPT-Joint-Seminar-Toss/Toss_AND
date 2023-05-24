package com.example.toss_and.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentTemppayBinding
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor

class TempPayFragment : BindingFragment<FragmentTemppayBinding>(R.layout.fragment_temppay) {
    override fun onResume() {
        super.onResume()
        setStatusBarColor(requireActivity(), R.color.white)
    }
}