package com.shashank.toptopic.data.entites

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.shashank.toptopic.other.const.DEFAULT_PROFILE_PICTURE
@IgnoreExtraProperties
data class User(
    val uid:String = "",
    val username:String="",
    val profilePictureUrl:String =DEFAULT_PROFILE_PICTURE,
    val description:String="",
    val followers:List<String> = listOf(),
    @Exclude
    var onFollowing:Boolean = false

)
