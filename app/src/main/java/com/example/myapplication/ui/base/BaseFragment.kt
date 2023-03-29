package com.example.myapplication.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollectors(savedInstanceState)
        initStaticObservers(savedInstanceState)
    }

    abstract fun initCollectors(savedInstanceState: Bundle?)
    abstract fun initStaticObservers(savedInstanceState: Bundle?)
}