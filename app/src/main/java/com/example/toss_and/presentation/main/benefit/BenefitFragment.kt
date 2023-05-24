package com.example.toss_and.presentation.main.benefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentBenefitBinding
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor

class BenefitFragment : BindingFragment<FragmentBenefitBinding>(R.layout.fragment_benefit) {
    override fun onResume() {
        super.onResume()
        setStatusBarColor(requireActivity(), R.color.white)
    }
}