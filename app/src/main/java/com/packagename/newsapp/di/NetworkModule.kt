package com.packagename.newsapp.di

import com.packagename.newsapp.api.AuthInterceptor
import com.packagename.newsapp.api.NotesApi
import com.packagename.newsapp.api.UserApi
import com.packagename.newsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder() : Retrofit.Builder{
         return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)

    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build() //here we use okhttpclient to add our authinterceptor in it

    }

    @Singleton
    @Provides
    fun providesUserApi(retrofitBuilder: Retrofit.Builder) : UserApi{
        return retrofitBuilder.build().create(UserApi::class.java)

    }

    @Singleton
    @Provides
    fun providesNoteApi(retrofitBuilder: Builder, okHttpClient: OkHttpClient) : NotesApi{
        return retrofitBuilder
            .client(okHttpClient)                 //here we add our okhttpclient which privide us with token
            .build()
            .create(NotesApi::class.java)
    }

}