package com.shashank.toptopic.main.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.shashank.toptopic.R
import com.shashank.toptopic.main.viewmodel.createPostViewModel
import com.shashank.toptopic.other.EventObserver
import com.shashank.toptopic.ui.auth.AuthViewModel
import com.shashank.toptopic.ui.snakebar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.creat_postfragment.*

@AndroidEntryPoint
class CreatePostFragment :Fragment(R.layout.creat_postfragment) {

    private lateinit var viewModel : createPostViewModel
    private var imageUri: Uri? = null
    private lateinit var process: ProgressDialog


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            imageUri= uri

            // Use Uri object instead of File to avoid storage permissions
            ivPostImage.setImageURI(imageUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        process= ProgressDialog(context,R.style.Base_ThemeOverlay_MaterialComponents_MaterialAlertDialog)
        process.setMessage("Loading...")
        process.setCancelable(false)
        viewModel= ViewModelProvider(requireActivity()).get(createPostViewModel::class.java)


        btnSetPostImage.setOnClickListener {

            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()



        }



        subscribeToObserver()
        btnPost.setOnClickListener {
            subscribeToObserver()
            if(imageUri==null){
                snakebar("Please select image")
            }
            imageUri?.let { it1 -> viewModel.createPost(it1,etPostDescription.text.toString()) }

        }

    }

    @SuppressLint("FragmentLiveDataObserve")
    fun subscribeToObserver(){

        viewModel.createPostStatus.observe(this,EventObserver(
            onError = {
                process.dismiss()
                snakebar(it)

            },
            onLoading = {

                process.show()
            }
        ){
            process.dismiss()
            findNavController().popBackStack()
            snakebar("SuccessFully Posted")
        })



    }

}