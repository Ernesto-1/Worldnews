package com.example.worldnews.ui.newsSports

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
import com.example.worldnews.databinding.FragmentNewsSportsBinding
import com.example.worldnews.repository.NewsRepositoryImpl
import com.example.worldnews.repository.RetrofitClient
import com.example.worldnews.ui.adapter.NewsSportsAdapter
import com.example.worldnews.viewmodel.NewsSportsViewModelFactory
import com.example.worldnews.viewmodel.NewsViewModel


class NewsSportsFragment : Fragment(R.layout.fragment_news_sports), NewsSportsAdapter.OnNewsClickListener {

    private val viewModel by viewModels<NewsViewModel>{
        NewsSportsViewModelFactory(NewsRepositoryImpl(NewsDataSource(RetrofitClient.webservice)))
    }

    private lateinit var binding: FragmentNewsSportsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsSportsBinding.bind(view)

        viewModel.fetchSports().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.shimmerViewContainer.startShimmer()
                }
                is Resource.Success -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.rvNews.adapter = NewsSportsAdapter(result.data.articles, this@NewsSportsFragment)
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
        val action = NewsSportsFragmentDirections.actionNewsSportsFragmentToNewsDetailsFragment(
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
        findNavController().navigate(action)    }


}