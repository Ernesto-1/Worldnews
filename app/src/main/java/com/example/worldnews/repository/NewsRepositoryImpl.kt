package com.example.worldnews.repository

import com.example.worldnews.data.model.newsList
import com.example.worldnews.data.remote.NewsDataSource

class NewsRepositoryImpl(private val datasource: NewsDataSource): NewsRepository {
    override suspend fun getNewsEU(): newsList = datasource.getNewsEU()
    override suspend fun getNewsTecnology(): newsList = datasource.getNewsTecnology()
    override suspend fun getNewsSports(): newsList = datasource.getNewsSports()

}