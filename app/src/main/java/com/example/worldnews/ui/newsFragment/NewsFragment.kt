package com.example.worldnews.ui.newsFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.worldnews.R
import com.example.worldnews.core.Resource
import com.example.worldnews.data.model.News
import com.example.worldnews.data.remote.NewsDataSource
import com.example.worldnews.databinding.FragmentNewsBinding
import com.example.worldnews.repository.NewsRepositoryImpl
import com.example.worldnews.repository.RetrofitClient
import com.example.worldnews.ui.adapter.NewsEuAdapter
import com.example.worldnews.viewmodel.NewsViewModel
import com.example.worldnews.viewmodel.NewsViewModelFactory


class NewsFragment : Fragment(R.layout.fragment_news), NewsEuAdapter.OnNewsClickListener {

    private val viewModel by viewModels<NewsViewModel>{
        NewsViewModelFactory(NewsRepositoryImpl(NewsDataSource(RetrofitClient.webservice)))
    }
    private lateinit var binding: FragmentNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        viewModel.fetchNews().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.shimmerViewContainer.startShimmer()
                }
                is Resource.Success -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.rvNews.adapter = NewsEuAdapter(result.data.articles, this@NewsFragment)
                    binding.shimmerViewContainer.visibility = View.GONE

                }
                is Resource.Failure -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                    Log.e("FetchError", "Error: $result.exception ")
                    Toast.makeText(requireContext(), "Error: ${result.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

    }

    override fun onNewsClick(news: News) {
        val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(
            news.id,
            news.name,
            news.author,
            news.title,
            news.description,
            news.url,
            news.urlToImage,
            news.publishedAt,
            news.content

        )
        findNavController().navigate(action)
    }


}