package com.example.worldnews.repository

import com.example.worldnews.data.model.newsList

interface NewsRepository {

    suspend fun getNewsEU(): newsList
    suspend fun getNewsTecnology(): newsList
    suspend fun getNewsSports(): newsList


}