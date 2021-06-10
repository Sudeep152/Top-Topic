package com.shashank.toptopic.main.viewmodel

import android.content.Context
import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.toptopic.R
import com.shashank.toptopic.other.Event
import com.shashank.toptopic.other.Resource
import com.shashank.toptopic.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class createPostViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val applicationContext: Context,
    private val dispatcher:CoroutineDispatcher=Dispatchers.Main
) :ViewModel(){

    private val _createPostStatus = MutableLiveData<Event<Resource<Any>>>()
    val createPostStatus:LiveData<Event<Resource<Any>>> = _createPostStatus

    private val _curImageUri = MutableLiveData<Uri>()
    val curImageUri: LiveData<Uri> = _curImageUri

    fun setCurImageUri(uri: Uri) {
        _curImageUri.postValue(uri)
    }

    fun createPost(imageUri:Uri,text:String){

        if (text.isEmpty()){
            val error=applicationContext.getString(R.string.error_input_empty)
            _createPostStatus.postValue(Event(Resource.Error(error)))
        }else{

            _createPostStatus.postValue(Event(Resource.Loading()))
            viewModelScope.launch(dispatcher) {

                val result = repository.createPost(imageUri,text)
                _createPostStatus.postValue(Event(result))


            }
        }


    }

}