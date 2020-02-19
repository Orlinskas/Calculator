package ua.brander.meetingroom.data.storage.model

data class ResponseWithListData<T>(val code: Int, val message: String, val data: List<T>)
