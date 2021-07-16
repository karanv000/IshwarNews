package com.news.network

import com.news.models.NewsViewModel
import dagger.Component

@Component(modules = [APIsClient::class])
interface ApiComponent {

    fun inject(service: APIsCaller)

    fun inject(viewModel: NewsViewModel)

}