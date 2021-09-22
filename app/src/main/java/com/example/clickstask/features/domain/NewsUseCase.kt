package com.example.clickstask.features.domain

import com.example.clickstask.core.network.remote.APIResult
import com.example.clickstask.core.network.remote.Resource
import com.example.clickstask.features.data.NewsRepository
import com.example.clickstask.features.data.network.model.NewsResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsUseCase(private val newsRepo: NewsRepository) {

    fun getNews(): Flow<Resource<NewsResponseModel>> {
        return newsRepo.getNews().map {
            when (it) {
                is APIResult.Success -> {
                    Resource.Success(it.data!!)
                }

                is APIResult.Error -> Resource.Error(it.errorTypes)
            }
        }
    }

}