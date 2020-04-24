package com.example.calculator.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.core.observeWith
import com.example.calculator.databinding.CalculatorFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorFragment : Fragment() {

    companion object {
        fun newInstance() = CalculatorFragment()
    }

    private val viewModel: CalculatorViewModel by viewModel()

    lateinit var binding: CalculatorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.calculator_fragment, container, false
        )
        binding.viewModel = viewModel
        (binding.viewModel as CalculatorViewModel).viewState.observeWith(this) {
            with(binding) {
                viewState = it
            }
        }

        return binding.root
    }

    fun onClickFriend(view: View?) {
        Log.i("", "Now Friend")
    }

}
