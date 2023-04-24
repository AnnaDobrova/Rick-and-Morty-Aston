package com.example.rickandmorty.utils

import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import java.util.concurrent.TimeUnit

class AnimationFactory {

    fun getShakeErrorAnimation(
        duration: Long = TimeUnit.SECONDS.toMillis(1)
    ) = TranslateAnimation(0F, 10F, 0F, 0F).apply {
        this.duration = duration
        interpolator = CycleInterpolator(7F)
    }
}