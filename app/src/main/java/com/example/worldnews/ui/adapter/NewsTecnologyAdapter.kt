package com.example.worldnews.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldnews.core.BaseViewHolder
import com.example.worldnews.data.model.News
import com.example.worldnews.databinding.ItemListBinding

class NewsTecnologyAdapter(private val newsL : List<News>,  private val itemClickListener: NewsTecnologyAdapter.OnNewsClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnNewsClickListener{
        fun onNewsClick(news: News)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itembinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MainViewHolder(itembinding, parent.context)

        itembinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?:return@setOnClickListener
            itemClickListener.onNewsClick(newsL[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(newsL[position])
        }
    }

    override fun getItemCount(): Int = newsL.size

    inner class MainViewHolder(val binding: ItemListBinding, val context: Context): BaseViewHolder<News>(binding.root){
        override fun bind(item: News) {
            Glide.with(context).load(item.urlToImage).into(binding.itemImage)
            binding.itemTitle.text = item.title
        }

    }
}