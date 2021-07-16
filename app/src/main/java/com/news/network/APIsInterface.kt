package com.news.network

import com.news.models.NewsDataModel
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Muhammed Refaat on 30/1/2021.
 */

interface APIsInterface {

    @GET("viewed//{period}.json")
    fun currentNyTimesNews(
        @Path(value = "period", encoded = true) period: String,
        @Query("api-key") apiKey: String
    ): Single<NewsDataModel>

}