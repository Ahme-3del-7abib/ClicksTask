package com.example.clickstask.features.data.network.model

import com.example.clickstask.core.network.sharedModels.NetworkResponse
import com.google.gson.annotations.SerializedName

data class NewsResponseModel(
    @SerializedName("articles") val articles: List<ArticleModel>
) : NetworkResponse

data class ArticleModel(
    @SerializedName("source") val source: SourceModel?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("urlToImage") val image: String?
)

data class SourceModel(
    @SerializedName("id") val sourceId: String?,
    @SerializedName("name") val sourceName: String?
)