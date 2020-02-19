package ua.brander.meetingroom.data.storage.model

data class ResponseData<T>(val code:Int, val message:String, val data: T)