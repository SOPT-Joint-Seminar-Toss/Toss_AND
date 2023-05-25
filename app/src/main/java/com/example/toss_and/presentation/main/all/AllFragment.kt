package com.example.toss_and.presentation.main.all

import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentAllBinding
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor

class AllFragment : BindingFragment<FragmentAllBinding>(R.layout.fragment_all) {
    override fun onResume() {
        super.onResume()
        setStatusBarColor(requireActivity(), R.color.white)
    }
}