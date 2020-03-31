package com.shid.moviechecker.Api

import com.shid.moviechecker.Model.MovieDetails
import com.shid.moviechecker.Model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id:Int):Single<MovieDetails>

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>
}