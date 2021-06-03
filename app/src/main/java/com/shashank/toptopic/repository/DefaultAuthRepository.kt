package com.shashank.toptopic.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shashank.toptopic.data.entites.User
import com.shashank.toptopic.other.Resource
import com.shashank.toptopic.other.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DefaultAuthRepository:AuthRepository {

  val mAuth= FirebaseAuth.getInstance()
  val usersDb = FirebaseFirestore.getInstance().collection("Users")

    override suspend fun register(
        email: String,
        password: String,
        username: String
    ): Resource<AuthResult> {

        return withContext(Dispatchers.IO) {
            safeCall {
                val result = mAuth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid!!
                val user = User(uid, username)
                usersDb.document(uid).set(user).await()
                Resource.Success(result)
            }
        }

    }

    override suspend fun login(email: String, password: String): Resource<AuthResult> {

      TODO()

    }
}