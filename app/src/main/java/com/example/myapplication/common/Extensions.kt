package com.example.myapplication.common

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//region View

fun View.visible() { this.visibility = View.VISIBLE }

fun View.gone() { this.visibility = View.GONE }

//endregion

//region Fragment

fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collector: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collector)
        }
    }
}

fun Fragment.showToast(msg: String) = Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()

//endregion
