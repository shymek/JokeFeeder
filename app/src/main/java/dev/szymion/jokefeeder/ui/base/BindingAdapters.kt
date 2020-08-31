package dev.szymion.jokefeeder.ui.base

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleIf")
    fun visibleIf(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("delayedVisibleIf")
    fun delayedVisibleIf(view: View, visible: Boolean) {
        if (visible) {
            view.visibility = View.VISIBLE
        } else {
            view.postDelayed({ view.visibility = View.GONE }, DEFAULT_ANIMATION_LENGTH)
        }
    }

    private const val DEFAULT_ANIMATION_LENGTH = 500L
}
