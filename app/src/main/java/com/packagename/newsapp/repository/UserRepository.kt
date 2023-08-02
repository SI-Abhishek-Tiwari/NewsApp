package com.packagename.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.packagename.newsapp.api.UserApi
import com.packagename.newsapp.models.UserRequest
import com.packagename.newsapp.models.UserResponse
import com.packagename.newsapp.utils.Constants.TAG
import com.packagename.newsapp.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


//repository use to interact with remote data source or local data source we use here remote
class UserRepository @Inject constructor(private val userApi: UserApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData

    suspend fun registerUser(userRequest: UserRequest) {
        val response = userApi.signup(userRequest)
        Log.d(TAG, response.body().toString())
        handleResponse(response)
    }

    suspend fun loginUser(userRequest: UserRequest) {
        val response = userApi.signin(userRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {           //in success we get json converted in kotlin object but in error we have to create json in obj
            val errorObj =
                JSONObject(                        //here jsonobj used to convert json in kotlin obj
                    response.errorBody()!!.charStream().readText()
                )
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))  //here message is the key in erroy body
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


}