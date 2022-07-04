package com.example.worldnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.worldnews.core.Resource
import com.example.worldnews.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class NewsViewModel(private val repo: NewsRepository): ViewModel(){

    fun fetchNews() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getNewsEU()))

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchTecnology() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getNewsTecnology()))

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchSports() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getNewsSports()))

        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


}




//Crear instancia de repo
class NewsViewModelFactory(private val repo:NewsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NewsRepository::class.java).newInstance(repo)
    }

}



//Crear instancia de repo
class NewsTecnologyViewModelFactory(private val repo:NewsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NewsRepository::class.java).newInstance(repo)
    }



}


class NewsSportsViewModelFactory(private val repo:NewsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NewsRepository::class.java).newInstance(repo)
    }



}