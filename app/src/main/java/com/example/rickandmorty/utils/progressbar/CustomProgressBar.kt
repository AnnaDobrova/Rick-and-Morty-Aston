package com.example.rickandmorty.utils.progressbar

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import com.example.rickandmorty.R

class CustomProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    IProgressView {

    companion object {
        private const val INVISIBLE_ALPHA = 0f
        private const val VISIBLE_ALPHA = 1f
        private const val STATE_PROGRESS_DELAY_MS = 250L
        private const val ANIMATION_TIME_MS = 300L
        private const val ROTATE_ANIMATION_DURATION_MS = 600L
        private const val ROTATE_ANGLE_FULL = 360f
        private const val ROTATE_ANGLE_NONE = 0f
        private const val DEFAULT_PIVOT_X_VALUE = 0.5f
        private const val DEFAULT_PIVOT_Y_VALUE = 0.5f
        private const val ARC_PROGRESS_PERCENT = 50
    }

    private var progressBar: ArchProgressWidget? = null
    private var rotateAnimation: RotateAnimation? = null

    private val hideProgressRunnable = Runnable { startFadeOutAnimation() }
    private val showProgressRunnable = Runnable { startFadeInAnimation() }
    private val animationHandler = Handler()

    init {
        inflate(context, R.layout.progress_bar_default, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        progressBar = findViewById(R.id.default_progress_bar)
        progressBar?.loadWidget(ARC_PROGRESS_PERCENT)
        alpha = if (visibility == View.VISIBLE) VISIBLE_ALPHA else INVISIBLE_ALPHA
    }

    override fun showProgress() {
        animationHandler.removeCallbacks(hideProgressRunnable)
        animationHandler.postDelayed(showProgressRunnable, STATE_PROGRESS_DELAY_MS)
    }

    override fun hideProgress() {
        animationHandler.removeCallbacks(showProgressRunnable)
        animationHandler.postDelayed(hideProgressRunnable, STATE_PROGRESS_DELAY_MS)
    }

    override fun onVisibilityChanged(
        changedView: View,
        visibility: Int
    ) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            progressBar?.startAnimation(getRotateAnimation())
        } else {
            getRotateAnimation().cancel()
        }
    }

    /**
     * Объединяет логику 2 методов: showProgress, hideProgress.
     * Так же используется в databinding для контроля видимости через LiveData,
     * app:visibility="@{vm.loading}"
     */
    fun setVisibility(isVisible: Boolean) {
        if (isVisible) showProgress()
        else hideProgress()
    }

    private fun getRotateAnimation() = rotateAnimation
        ?: RotateAnimation(
            ROTATE_ANGLE_NONE, ROTATE_ANGLE_FULL,
            Animation.RELATIVE_TO_SELF, DEFAULT_PIVOT_X_VALUE,
            Animation.RELATIVE_TO_SELF, DEFAULT_PIVOT_Y_VALUE
        ).apply {
            rotateAnimation = this
            duration = ROTATE_ANIMATION_DURATION_MS
            repeatCount = Animation.INFINITE
            interpolator = LinearInterpolator()
        }

    private fun startFadeInAnimation() {
        ViewCompat.animate(this)
            .withStartAction { visibility = View.VISIBLE }
            .alpha(VISIBLE_ALPHA)
            .setDuration(ANIMATION_TIME_MS)
            .setInterpolator(BezierInterpolator.interpolator)
            .start()
    }

    private fun startFadeOutAnimation() {
        ViewCompat.animate(this)
            .alpha(INVISIBLE_ALPHA)
            .setDuration(ANIMATION_TIME_MS)
            .withEndAction { visibility = View.GONE }
            .setInterpolator(BezierInterpolator.interpolator)
            .start()
    }
}
