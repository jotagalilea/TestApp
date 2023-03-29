package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.common.*
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModel()


    override fun initCollectors(savedInstanceState: Bundle?) {
        collectLatestLifecycleFlow(viewModel.loginSharedFlow) { state ->
            handleLoginState(state)
        }
    }

    override fun initStaticObservers(savedInstanceState: Bundle?) {
        with(binding) {
            buttonEnter.setOnClickListener {
                viewModel.login(tilUser.text.toString(), tilPassword.text.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun handleLoginState(state: State) {
        when (state) {
            is State.Empty -> {
                binding.pbLoading.gone()
                showToast(state.message)
            }
            is State.Error -> {
                binding.pbLoading.gone()
                showToast(state.message)
            }
            State.Loading -> {
                binding.pbLoading.visible()
            }
            is State.Success<*> -> {
                showToast(state.data as String)
                (activity as MainActivity).navController.navigate(
                    R.id.action_mainFragment_to_detailFragment
                )
            }
            State.Waiting -> Unit
        }
    }

}