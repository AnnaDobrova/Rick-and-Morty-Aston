package com.example.rickandmorty.utils.progressbar

import android.view.animation.Interpolator
import androidx.core.view.animation.PathInterpolatorCompat

object BezierInterpolator {
    private const val X = 0.25f
    private const val START_Y = 0.1f
    private const val FINISH_Y = 1f
    val interpolator: Interpolator
        get() = PathInterpolatorCompat.create(X, START_Y, X, FINISH_Y)
}