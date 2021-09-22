package com.example.clickstask.core.network.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object BaseClient {

    fun getBaseService(
        url: String
    ): Retrofit.Builder {

        val retrofit: Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(url)
                .client(getClient(OkHttpClient.Builder(), HttpLoggingInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create())
        }

        return retrofit
    }

    private fun getClient(
        okhttpClient: OkHttpClient.Builder,
        logInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {

        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okhttpClient.apply {
            connectTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            readTimeout(5, TimeUnit.MINUTES)
            okhttpClient.addInterceptor(logInterceptor)
        }

        return okhttpClient
    }
}