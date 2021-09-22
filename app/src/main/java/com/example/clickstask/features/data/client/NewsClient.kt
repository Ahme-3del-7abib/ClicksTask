package com.example.clickstask.features.data.client

import com.example.clickstask.core.network.base.BaseClient.getBaseService
import com.example.clickstask.core.utils.Constants
import com.example.clickstask.features.data.network.NewsApi

class NewsClient {

    val newsServices: NewsApi by lazy {
        getBaseService(Constants.BASE_URL)
            .build()
            .create(NewsApi::class.java)
    }

}