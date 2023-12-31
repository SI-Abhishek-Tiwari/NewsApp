package com.packagename.newsapp

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packagename.newsapp.models.UserRequest
import com.packagename.newsapp.models.UserResponse
import com.packagename.newsapp.repository.UserRepository
import com.packagename.newsapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest){

        viewModelScope.launch{
            userRepository.registerUser(userRequest)
        }

    }

    fun loginUser(userRequest: UserRequest){

        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }

    }


    fun validateCredentials(username:String, emailAddress : String , password : String , isLogin:Boolean) :Pair<Boolean,String>{  //here pair used to give both string and boolean data type
        var result = Pair(true,"")
        if (!!isLogin && TextUtils.isEmpty(username) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)){         //in login we dont need username so in loginfragment we set is login true and here bcz of ! it will became false and bcz of && username become false so it will move forward
            result = Pair(false,"Please provide the credentials")
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){  //here our email address get compare to patterns email address it comes by rajax
            result = Pair(false,"Please provide valid email")
        }
        else if (password.length <=5){
            result = Pair(false,"Password should be greater than 5")
        }
        return  result
    }
}