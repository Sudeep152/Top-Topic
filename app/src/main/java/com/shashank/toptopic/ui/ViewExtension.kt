package com.shashank.toptopic.ui

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.shashank.toptopic.R

fun View.slideUpSingle(context: Context , animTime:Long , starToffSet:Long){

    val slideup = AnimationUtils.loadAnimation(context, R.anim.slide_up).apply {
        duration = animTime
        interpolator= FastOutSlowInInterpolator()
        this.startOffset = starToffSet
    }
    startAnimation(slideup)
}

fun slideUAllView(context: Context,vararg views:View,animTime: Long=300L,starToffSet: Long=150L){

    for (i in views.indices){
        views[i].slideUpSingle(context,animTime,starToffSet*i)
    }
}