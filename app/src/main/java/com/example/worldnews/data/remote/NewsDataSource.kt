package com.example.worldnews.data.remote

import com.example.worldnews.data.model.newsList
import com.example.worldnews.repository.WebService
import com.example.worldnews.utils.AppConstants

class NewsDataSource(private val webService: WebService) {

    suspend fun getNewsEU(): newsList = webService.getNewsEU(AppConstants.EU,AppConstants.API_KEY)
    suspend fun getNewsTecnology(): newsList = webService.getNewsTecnology(AppConstants.TECHNOLOGY,AppConstants.API_KEY)
    suspend fun getNewsSports(): newsList = webService.getNewsSports(AppConstants.SPORTS,AppConstants.API_KEY)

}