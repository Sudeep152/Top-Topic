package com.shashank.toptopic.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.shashank.toptopic.data.entites.Post
import com.shashank.toptopic.data.entites.User
import com.shashank.toptopic.other.Resource
import com.shashank.toptopic.other.safeCall
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class DefaultMainRepository :MainRepository {

    private val mAuth =FirebaseAuth.getInstance()
    private val fireStore =FirebaseFirestore.getInstance()
    private val  storage= FirebaseStorage.getInstance()
    private val users = fireStore.collection("Users")
    private val posts =fireStore.collection("Post")
    private val comments =fireStore.collection("Comments")

    override suspend fun createPost(imageUri: Uri, text: String): Resource<Any> = withContext(Dispatchers.IO){

        safeCall {
            val uID= mAuth.uid!!
            val postId =UUID.randomUUID().toString()
            val imageUploadResult = storage.getReference(postId).putFile(imageUri).await()
            val imageUrl = imageUploadResult?.metadata?.reference?.downloadUrl?.await().toString()
            val  post =Post(
                postId,
                uID,
                text=text,
                data = System.currentTimeMillis(),
                imageUrl = imageUrl
            )
            posts.document(postId).set(post).await()
            Resource.Success(Any())
        }

    }

    override suspend fun getAllUser(userUidS: List<String>): Resource<List<User>> = withContext(Dispatchers.IO){

        safeCall {

            val usersList = users.whereIn("uid",userUidS).orderBy("username").get().await().toObjects(User::class.java)
            Resource.Success(usersList)
        }


    }



}