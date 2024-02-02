package com.prography.quest.binding

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.prography.quest.R

object ViewBinding {

    // DetailPhotoDialog
    @JvmStatic
    @BindingAdapter("setBookmarkButton")
    fun bindSetBookmarkButton(view: ImageView, isBookmarked: Boolean) {
        view.setColorFilter(
            if (isBookmarked)
                ContextCompat.getColor(view.context, R.color.white)
            else
                ContextCompat.getColor(view.context, R.color.gray95)
        )
    }
}