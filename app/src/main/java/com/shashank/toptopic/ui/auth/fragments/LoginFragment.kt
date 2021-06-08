package com.shashank.toptopic.ui.auth.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shashank.toptopic.main.MainActivity
import com.shashank.toptopic.R
import com.shashank.toptopic.other.EventObserver
import com.shashank.toptopic.ui.auth.AuthViewModel
import com.shashank.toptopic.ui.snakebar
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var viewModel :AuthViewModel
    private lateinit var process: ProgressDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        process= ProgressDialog(context,R.style.Base_ThemeOverlay_MaterialComponents_MaterialAlertDialog)
        process.setMessage("Loading...")
        process.setCancelable(false)


        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        subscribeToObserver()
        loginbtn.setOnClickListener {
            viewModel.login(emailEdt.text.toString(),passEdt.text.toString())
        }




        textgotoregister.setOnClickListener {
            if(findNavController().previousBackStackEntry !=null){
                findNavController().popBackStack()
            }else{
                findNavController().navigate(R.id.action_loginFragment_to_regisFragment)
            }

        }
    }
    fun  subscribeToObserver(){

        viewModel.loginStatus.observe(viewLifecycleOwner,EventObserver(
            onError = {
               process.dismiss()

                snakebar(it)
            }
        ,onLoading = {


                     process.show()

            },onSuccess = {
                process.dismiss()
                   Intent(requireContext(), MainActivity::class.java).also {
                       startActivity(it)
                       requireActivity().finish()
                   }
                  snakebar("Successfully login ")
            }
        ))
    }



}