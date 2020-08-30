package dev.szymion.jokefeeder.ui.base

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("titleId")
    fun setTitleId(toolbar: Toolbar, @StringRes titleId: Int) {
        toolbar.setTitle(titleId)
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun visibleIf(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
