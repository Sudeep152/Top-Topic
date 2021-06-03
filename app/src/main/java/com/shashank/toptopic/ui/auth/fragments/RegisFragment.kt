package com.shashank.toptopic.ui.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shashank.toptopic.R
import kotlinx.android.synthetic.main.fragment_regis.*

class RegisFragment : Fragment(R.layout.fragment_regis) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textgotologin.setOnClickListener {
            if(findNavController().previousBackStackEntry !=null){
                findNavController().popBackStack()
            }else{
                findNavController().navigate(R.id.action_regisFragment_to_loginFragment)
            }


        }
    }

}