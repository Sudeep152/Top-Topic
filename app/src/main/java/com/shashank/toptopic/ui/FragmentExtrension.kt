package com.shashank.toptopic.ui

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snakebar(text:String){
    Snackbar.make(requireView(),text,Snackbar.LENGTH_SHORT).show()
}