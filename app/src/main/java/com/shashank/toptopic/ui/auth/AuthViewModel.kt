package com.shashank.toptopic.ui.auth

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.auth.AuthResult
import com.shashank.toptopic.R
import com.shashank.toptopic.other.Event
import com.shashank.toptopic.other.Resource
import com.shashank.toptopic.other.const.MIN_PASS
import com.shashank.toptopic.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Error

class AuthViewModel @ViewModelInject constructor(
    private val repository: AuthRepository,
   private val applicationContext: Context,
    private val dispatcher: CoroutineDispatcher= Dispatchers.Main

) :ViewModel(){

     private val _registerStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val  registerStatus :LiveData<Event<Resource<AuthResult>>> = _registerStatus

private val _loginStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val loginStatus :LiveData<Event<Resource<AuthResult>>> =_loginStatus


    fun login(email: String,password: String){

      val error=  if(email.isEmpty() || password.isEmpty()){
            applicationContext.getString(R.string.error_input_empty)
        }else null

        error?.let {
            _loginStatus.postValue(Event(Resource.Error(it)))

            return
        }
        _loginStatus.postValue(Event(Resource.Loading()))

    viewModelScope.launch (dispatcher){

        val result = repository.login(email,password)
        _loginStatus.postValue(Event(result))
      }

    }


    fun register(email:String,username:String,password:String,repassword:String){

        val error = if(email.isEmpty() ||username.isEmpty() ||password.isEmpty() || repassword.isEmpty()){
            applicationContext.getString(R.string.error_input_empty)
        }else if(password != repassword ){
            applicationContext.getString(R.string.repeat_password)
        }else if(password.length < MIN_PASS){
             applicationContext.getString(R.string.error_password_too_short)
        } else null

        error?.let {
            _registerStatus.postValue(Event(Resource.Error(it)))
            return
        }
        _registerStatus.postValue(Event((Resource.Loading())))

        viewModelScope.launch(dispatcher) {
            val result = repository.register(email,password, username)
          _registerStatus.postValue(Event(result))

        }







    }




}