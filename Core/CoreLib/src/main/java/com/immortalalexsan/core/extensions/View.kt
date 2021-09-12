package com.immortalalexsan.core.extensions

import android.view.View

/**
 * Экстеншен для [View.GONE].
 */
fun View.gone() {
    this.visibility = View.GONE
}
