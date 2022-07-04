package com.example.worldnews.ui.tecnologyFragment

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
import com.example.worldnews.databinding.FragmentTecnologyBinding
import com.example.worldnews.repository.NewsRepositoryImpl
import com.example.worldnews.repository.RetrofitClient
import com.example.worldnews.ui.adapter.NewsTecnologyAdapter
import com.example.worldnews.ui.newsFragment.NewsFragmentDirections
import com.example.worldnews.viewmodel.NewsTecnologyViewModelFactory
import com.example.worldnews.viewmodel.NewsViewModel

class TecnologyFragment : Fragment(R.layout.fragment_tecnology), NewsTecnologyAdapter.OnNewsClickListener {

    private val viewModel by viewModels<NewsViewModel>{
        NewsTecnologyViewModelFactory(NewsRepositoryImpl(NewsDataSource(RetrofitClient.webservice)))
    }
    private lateinit var binding: FragmentTecnologyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTecnologyBinding.bind(view)

        viewModel.fetchTecnology().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.shimmerViewContainer.startShimmer()
                }
                is Resource.Success -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.rvNews.adapter = NewsTecnologyAdapter(result.data.articles, this@TecnologyFragment)
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
        val action = TecnologyFragmentDirections.actionTecnologyFragmentToNewsDetailsFragment(
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