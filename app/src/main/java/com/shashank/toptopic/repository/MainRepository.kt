package com.shashank.toptopic.repository

import android.net.Uri
import com.shashank.toptopic.other.Resource
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
interface MainRepository {

    suspend fun createPost(imageUri:Uri,text:String):Resource<Any>
}