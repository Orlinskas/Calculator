package ua.brander.meetingroom.extensions

import okhttp3.ResponseBody
import org.json.JSONObject

fun ResponseBody.getMessage(): String = JSONObject(this.string()).getString("message")