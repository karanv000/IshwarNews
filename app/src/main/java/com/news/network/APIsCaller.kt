package com.news.network

import com.news.utils.AppConstants
import com.news.models.NewsDataModel
import io.reactivex.Single
import javax.inject.Inject

class APIsCaller {

    @Inject
    lateinit var api: APIsInterface

    init {
        DaggerApiComponent.builder()
            .aPIsClient(APIsClient)
            .build().inject(this)
    }

    /**
     * Getting current NewYork Times news
     */
    fun getCurrentNews( period: String): Single<NewsDataModel> {
        return api.currentNyTimesNews( period, AppConstants.API_KEY)
    }

}