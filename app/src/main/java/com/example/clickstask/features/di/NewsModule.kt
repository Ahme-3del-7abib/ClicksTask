package com.example.clickstask.features.di

import com.example.clickstask.features.data.NewsRepository
import com.example.clickstask.features.data.client.NewsClient
import com.example.clickstask.features.data.network.NewsRemoteDataSource
import com.example.clickstask.features.domain.NewsUseCase
import com.example.clickstask.features.presentation.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    single { NewsClient() }
    single { NewsRemoteDataSource(get()) }
    single { NewsRepository(get()) }
    single { NewsUseCase(get()) }
}

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}