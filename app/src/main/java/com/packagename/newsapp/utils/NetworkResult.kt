package com.packagename.newsapp.utils

sealed class NetworkResult<T>(val data : T? = null , val message : String? = null){     //property(data)
    class Success<T>(data: T) : NetworkResult<T>(data)                                  //parameter(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data,message)
    class Loading<T> : NetworkResult<T>()
}
