package com.shashank.toptopic.ui.main.fragments

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.shashank.toptopic.R
import com.shashank.toptopic.ui.main.viewmodel.createPostViewModel
import com.shashank.toptopic.other.EventObserver
import com.shashank.toptopic.ui.auth.AuthViewModel
import com.shashank.toptopic.ui.slideUAllView
import com.shashank.toptopic.ui.snakebar
import com.shashank.toptopic.ui.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.creat_postfragment.*

@AndroidEntryPoint
class CreatePostFragment :Fragment(R.layout.creat_postfragment)  {

    private val viewModel : createPostViewModel by viewModels()
    private var imageUri: Uri? = null
    private lateinit var process: ProgressDialog


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val uri: Uri = data?.data!!
            imageUri= uri
            ivPostImage.setImageURI(imageUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserver()
        process= ProgressDialog(context,R.style.Base_ThemeOverlay_MaterialComponents_MaterialAlertDialog)
        process.setMessage("Loading...")
        process.setCancelable(false)
        slideUAllView(requireContext(),ivPostImage,btnSetPostImage,tilPostText,btnPost)

        btnSetPostImage.setOnClickListener {

            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()



        }


        btnPost.setOnClickListener {

            if(imageUri==null){
                toast("Please select image")
            }
            imageUri?.let { it1 -> viewModel.createPost(it1,etPostDescription.text.toString()) }

        }



    }

    @SuppressLint("FragmentLiveDataObserve")
    fun subscribeToObserver(){
        viewModel.curImageUri.observe(viewLifecycleOwner) {
            imageUri = it


        }
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
            toast("Successfully uploaded")
        })



    }

}