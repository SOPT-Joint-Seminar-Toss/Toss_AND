package com.example.toss_and.presentation.main.benefit

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