package com.example.clickstask.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clickstask.core.base.BaseViewModel
import com.example.clickstask.core.network.base.ErrorTypes
import com.example.clickstask.core.network.remote.Resource
import com.example.clickstask.features.domain.NewsUseCase
import com.example.clickstask.features.domain.models.NewsItems
import com.example.clickstask.features.domain.models.toDataItems
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class NewsViewModel(private val useCase: NewsUseCase) : BaseViewModel() {

    private val _getNewsResponse = MutableLiveData<Resource<List<NewsItems>>>()
    val getNewsResponse: LiveData<Resource<List<NewsItems>>> = _getNewsResponse

    fun getNews() {
        useCase.getNews().onStart {
            _getNewsResponse.value = Resource.Loading(true)
        }.map {
            when (it) {
                is Resource.Success -> {
                    if (it.data == null) {
                        _getNewsResponse.value = Resource.Error(ErrorTypes.NoData())
                    } else {
                        _getNewsResponse.value =
                            Resource.Success(it.data.articles.toDataItems())
                    }
                }

                is Resource.Error -> {
                    handleErrorTypes(it.errorTypes)
                    _getNewsResponse.value = Resource.Error(it.errorTypes)
                }

                else -> it
            }
        }.launchIn(viewModelScope)
    }
}