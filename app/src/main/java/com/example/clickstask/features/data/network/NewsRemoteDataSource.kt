package com.example.clickstask.features.data.network

import com.example.clickstask.core.network.base.BaseRemoteDataSource
import com.example.clickstask.core.network.remote.APIResult
import com.example.clickstask.features.data.client.NewsClient
import com.example.clickstask.features.data.network.model.ArticleModel
import com.example.clickstask.features.data.network.model.NewsResponseModel
import com.example.clickstask.features.data.network.model.SourceModel
import kotlinx.coroutines.flow.flowOf

class NewsRemoteDataSource(private val client: NewsClient) : BaseRemoteDataSource() {

    fun getNews(country: String, apiKey: String) = safeApiCall {
        client.newsServices.getNews(country, apiKey)
    }

}