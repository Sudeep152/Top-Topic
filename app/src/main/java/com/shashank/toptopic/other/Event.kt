package com.shashank.toptopic.other

class Event<out   T>(private val content: T) {


    var hasBeenHandle = false
    private set

    fun  getContentIfNotHandled():T?{
        return if(!hasBeenHandle){
            hasBeenHandle=true
            content
        }else null
    }

    fun peekContent()=content
}