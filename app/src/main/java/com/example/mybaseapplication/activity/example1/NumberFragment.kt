package com.example.mybaseapplication.activity.example1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.core.extension.repeatOnLifecycleOnStarted
import com.example.mybaseapplication.databinding.FragmentNumberBinding

import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Shwet Desai on 14/12/23.
 * All rights reserved.
 * shwet.desai0@gmail.com
 */

@AndroidEntryPoint
class NumberFragment : Fragment() {
    private var _binding: FragmentNumberBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NumberViewModel by viewModels()

    class NumberCommaTextWatcher(
        private val binding: FragmentNumberBinding
    ) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // No action needed
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // No action needed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.btCalculate.isEnabled =
                binding.et1.text.isNotEmpty() && binding.et2.text.isNotEmpty() && binding.et3.text.isNotEmpty()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpInputFilter()
        setUpObserver()
        setUpOnClick()
        binding.btCalculate.isEnabled = false
    }

    private fun setUpInputFilter() {
        binding.et1.addTextChangedListener(NumberCommaTextWatcher(binding))
        binding.et2.addTextChangedListener(NumberCommaTextWatcher(binding))
        binding.et3.addTextChangedListener(NumberCommaTextWatcher(binding))
    }

    private fun setUpOnClick() {
        binding.btCalculate.setOnClickListener {
            viewModel.calculate(
                binding.et1.text.toString(),
                binding.et2.text.toString(),
                binding.et3.text.toString()
            )
        }
    }

    private fun setUpObserver() {
        repeatOnLifecycleOnStarted {
            viewModel.resultString.collect {
                binding.tvResult.text = it
            }
        }

//        repeatOnLifecycleOnStarted {
//            viewModel.vennData.collect {
//                Log.i("vennData", "it ${it.toString()}")
//                if(it.isNotEmpty() && it.size >= 3) {
//                    binding.vennDiagramView.visibility = View.VISIBLE
//                    binding.vennDiagramView.set1 = it[0]
//                    binding.vennDiagramView.set1 = it[1]
//                    binding.vennDiagramView.set1 = it[2]
//                    binding.vennDiagramView.invalidate()
//                }
//            }
//        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): NumberFragment = NumberFragment()

    }
}