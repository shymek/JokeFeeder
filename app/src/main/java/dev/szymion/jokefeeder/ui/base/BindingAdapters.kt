package dev.szymion.jokefeeder.ui.base

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("titleId")
    fun setTitleId(toolbar: Toolbar, @StringRes titleId: Int) {
        toolbar.setTitle(titleId)
    }
}
