package com.news.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.network.APIsCaller
import com.news.network.APIsClient
import com.news.network.DaggerApiComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.math.BigInteger
import javax.inject.Inject

class NewsViewModel : ViewModel() {

    @Inject
    lateinit var newsService: APIsCaller

    val newsList = HashMap<BigInteger, SingleNewsItem>()

    val newsListError = HashMap<String, ResponseBody?>()

    val newsData = MutableLiveData<Map<BigInteger, SingleNewsItem>>()

    val newsDataError = MutableLiveData<Map<String, ResponseBody?>>()

    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.builder()
            .aPIsClient(APIsClient)
            .build().inject(this)
    }

    fun getNewsDetails( period: String) {
        newsList.clear()
        newsListError.clear()
        newsData.value = null
        newsDataError.value = newsListError
        getNewsDetailsData(period)
    }

    private fun getNewsDetailsData( period: String) {
        newsService.getCurrentNews( period).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<NewsDataModel>() {
                override fun onSuccess(response: NewsDataModel?) {
                    loading.value = false
                    for (singleResult in response!!.results) {
                        try {
                            newsList[singleResult.id] = singleResult
                        } catch (ex: Exception) {
                            newsDataError.value = newsListError
                        }
                    }
                    newsData.value = newsList
                }

                override fun onError(e: Throwable?) {
                    loading.value = false
                    newsDataError.value = newsListError
                }
            },)
    }

}