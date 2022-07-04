package com.example.worldnews.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id : String = "",
    val name: String = "",
    val author: String = "",
    val title: String = "",
    val description : String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
): Parcelable

data class newsList(val articles: List<News> = listOf() )