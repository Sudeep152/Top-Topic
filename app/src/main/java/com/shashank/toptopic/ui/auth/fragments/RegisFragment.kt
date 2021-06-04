package com.shashank.toptopic.ui.auth.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.shashank.toptopic.R
import com.shashank.toptopic.other.EventObserver
import com.shashank.toptopic.ui.auth.AuthViewModel
import com.shashank.toptopic.ui.snakebar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_regis.*
@AndroidEntryPoint
class RegisFragment : Fragment(R.layout.fragment_regis) {
    private lateinit var viewModel :AuthViewModel
    private lateinit var process: ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        process = ProgressDialog(context)
        process.setTitle("Loading...")

        viewModel= ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        subscribeToObservers()
        regbtn.setOnClickListener {
            subscribeToObservers()
            viewModel.register(emailEdt.text.toString(),nameEdt.text.toString(),passEdt.text.toString()
            ,rePassEdt.text.toString())
        }




        textgotologin.setOnClickListener {
            if(findNavController().previousBackStackEntry !=null){
                findNavController().popBackStack()
            }else{
                findNavController().navigate(R.id.action_regisFragment_to_loginFragment)
            }


        }
    }


    fun subscribeToObservers(){

   viewModel.registerStatus.observe(viewLifecycleOwner,EventObserver(
       onError = {

                 process.dismiss()
           snakebar(it)
       }
   ,onLoading = {
       process.show()

       }
   ){
          snakebar(getString(R.string.success_registration))
       process.dismiss()
   })







    }



















}