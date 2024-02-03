package com.prography.quest.util

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun View.hide(activity: Activity){
    visibility = View.GONE
}

fun View.show(activity: Activity) {
    visibility = View.VISIBLE
}

fun <T> Fragment.collectLatestStateFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun createShimmerDrawable(): ShimmerDrawable {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(400)
        .setBaseAlpha(0.98f)
        .setHighlightAlpha(0.95f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}