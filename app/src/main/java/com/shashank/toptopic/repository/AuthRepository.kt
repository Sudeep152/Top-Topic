package com.shashank.toptopic.repository

import com.google.firebase.auth.AuthResult
import com.shashank.toptopic.other.Resource

interface AuthRepository {

    suspend fun register(email:String,
                         password:String,
                         username:String):Resource<AuthResult>


    suspend fun login(email: String,
                      password: String):Resource<AuthResult>


}