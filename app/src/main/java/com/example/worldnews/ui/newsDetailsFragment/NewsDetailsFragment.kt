package com.example.worldnews.ui.newsDetailsFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.worldnews.R
import com.example.worldnews.databinding.FragmentNewsDetailsBinding


class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    private val args by navArgs<NewsDetailsFragmentArgs>()

    private lateinit var binding: FragmentNewsDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)
        Glide.with(requireContext()).load(args.urlToImage).centerCrop().into(binding.imgNews)
        binding.txtauthor.text = args.author
        binding.txtTitle.text = args.title
        binding.txtpublic.text = args.publishedAt
        binding.txtContent.text = args.content

        binding.btnWeb.setOnClickListener {
            val url = args.url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


    }


}