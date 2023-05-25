package com.example.toss_and.presentation.main.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toss_and.R
import com.example.toss_and.databinding.FragmentStockBinding
import com.example.toss_and.util.base.BindingFragment
import com.example.toss_and.util.setStatusBarColor

class StockFragment : BindingFragment<FragmentStockBinding>(R.layout.fragment_stock) {
    override fun onResume() {
        super.onResume()
        setStatusBarColor(requireActivity(), R.color.white)
    }
}