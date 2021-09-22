package com.example.clickstask.features.domain.models

import com.example.clickstask.features.data.network.model.ArticleModel


data class NewsItems(
    val sourceName: String?,
    val title: String?,
    val description: String?,
    val image: String?
)

fun List<ArticleModel>.toDataItems(): List<NewsItems> {
    return map { it.toDataItem() }
}

fun ArticleModel.toDataItem(): NewsItems {
    return NewsItems(source?.sourceName, title, description, image)
}