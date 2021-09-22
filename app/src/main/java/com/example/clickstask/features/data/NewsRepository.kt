package com.example.clickstask.features.data

import com.example.clickstask.core.network.remote.APIResult
import com.example.clickstask.core.utils.Constants
import com.example.clickstask.features.data.network.NewsRemoteDataSource
import com.example.clickstask.features.data.network.model.NewsResponseModel
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val dataSource: NewsRemoteDataSource) {
    fun getNews(): Flow<APIResult<NewsResponseModel>> {
        return dataSource.getNews(Constants.country, Constants.apiKey)
    }
}