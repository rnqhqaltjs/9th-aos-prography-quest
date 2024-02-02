package com.prography.quest.util

import android.app.Activity
import android.view.View
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun View.hide(activity: Activity){
    visibility = View.GONE
}

fun View.show(activity: Activity) {
    visibility = View.VISIBLE
}

fun createShimmerDrawable(): ShimmerDrawable {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(800)
        .setBaseAlpha(0.98f)
        .setHighlightAlpha(0.95f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}