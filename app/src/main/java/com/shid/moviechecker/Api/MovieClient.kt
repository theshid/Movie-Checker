package com.shid.moviechecker.Api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "76f3f20c1bee3fe031c8336b00194dc1"
const val BASE_URL = "https://api.themoviedb.org/3/"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

// https://api.themoviedb.org/3/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1
// https://api.themoviedb.org/3/movie/299534?api_key=6e63c2317fbe963d76c3bdc2b785f6d1
// https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg

object MovieClient {

   fun getClient():MovieInterface{
       val requestInterceptor = Interceptor{chain ->
           val url:HttpUrl = chain.request()
               .url()
               .newBuilder()
               .addQueryParameter("api_key", API_KEY)
               .build()

           val request:Request = chain.request()
               .newBuilder()
               .url(url)
               .build()

           return@Interceptor chain.proceed(request)
       }

       val okHTTPClient:OkHttpClient = OkHttpClient().newBuilder()
           .addInterceptor(requestInterceptor)
           .connectTimeout(60,TimeUnit.SECONDS)
           .build()

       return Retrofit.Builder()
           .client(okHTTPClient)
           .baseUrl(BASE_URL)
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(MovieInterface::class.java)
   }
}