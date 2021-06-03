package com.shashank.toptopic.repository

import com.google.firebase.auth.AuthResult
import com.shashank.toptopic.other.Resource

class DefaultAuthRepository:AuthRepository {


    override suspend fun register(
        email: String,
        password: String,
        username: String
    ): Resource<AuthResult> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Resource<AuthResult> {
        TODO("Not yet implemented")
    }
}