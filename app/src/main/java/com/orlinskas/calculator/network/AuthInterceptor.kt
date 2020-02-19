package ua.brander.meetingroom.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val requestBuilder = req.newBuilder()//.addHeader(ACCEPT_LANGUAGE, Locale.getDefault().language)
        //requestBuilder.removeHeader(TOKEN)
        //requestBuilder.addHeader(TOKEN, token)
        req = requestBuilder.build()
        return chain.proceed(req)
    }
}