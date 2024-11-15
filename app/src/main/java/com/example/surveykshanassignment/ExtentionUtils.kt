package com.example.surveykshanassignment

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.animation.AnimationUtils

object ExtentionUtils {
    fun View.setOnClickThrottleBounceListener(throttleTime: Long = 600L, onClick: () -> Unit) {

        this.setOnClickListener(object : View.OnClickListener {

            private var lastClickTime: Long = 0
            override fun onClick(v: View) {
                context?.let {
                    v.bounce(context)
                    if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
                    else onClick()
                    lastClickTime = SystemClock.elapsedRealtime()
                }

            }
        })
    }
    fun View.bounce(context: Context, animDuration: Long = 500L) = run {
        this.clearAnimation()
        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
            .apply {
                duration = animDuration
            }
        this.startAnimation(animation)
    }

}