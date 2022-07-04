package com.example.worldnews.repository

import com.example.worldnews.data.model.newsList
import com.example.worldnews.utils.AppConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("top-headlines")
    suspend fun getNewsEU(
        @Query("country")country: String,
        @Query("apiKey")apiKey: String
    ): newsList

    @GET("top-headlines")
    suspend fun getNewsTecnology(
        @Query("category")category: String,
        @Query("apiKey")apiKey: String
    ): newsList

    @GET("top-headlines")
    suspend fun getNewsSports(
        @Query("category")category: String,
        @Query("apiKey")apiKey: String
    ): newsList



}

object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}