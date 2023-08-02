package com.packagename.newsapp.api

import com.packagename.newsapp.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor()  : Interceptor {
    //here we use interceptor to add token in authorization in our request

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()  //request that we are sending here we make new request of it and header to it

        val token = tokenManager.getToken()
        request.addHeader("Authorization","Bearer $token")

        return chain.proceed(request.build())  //here we made changes in our request now we can move forward
    }

}