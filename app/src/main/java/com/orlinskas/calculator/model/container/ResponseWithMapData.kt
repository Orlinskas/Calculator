package ua.brander.meetingroom.data.storage.model

data class ResponseWithMapData<T>(val code: Int, val message: String, val data: Map<String, T>)