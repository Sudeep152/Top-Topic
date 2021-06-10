package com.shashank.toptopic.ui

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snakebar(text:String){
    Snackbar.make(requireView(),text,Snackbar.LENGTH_SHORT).show()
}
fun Fragment.toast(text: String){
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}