package com.example.clickstask.features.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clickstask.databinding.ItemListNewsLayoutBinding
import com.example.clickstask.features.domain.models.NewsItems

class NewsRecyclerAdapter(private val onItemClickListener: OnNewsItemClickListener) :
    RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    private lateinit var context: Context
    private var newsList: List<NewsItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        context = parent.context
        val binding =
            ItemListNewsLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return NewsViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newCell = newsList[position]

        holder.binding.sourceNameTv.text = newCell.sourceName
        holder.binding.titleTv.text = newCell.title

        if (!newCell.image.isNullOrEmpty()) {
            Glide.with(context)
                .load(newCell.image)
                .into(holder.binding.newImg)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setList(list: List<NewsItems>) {
        this.newsList = list
        notifyDataSetChanged()
    }

    class NewsViewHolder(
        val binding: ItemListNewsLayoutBinding,
        private val listener: OnNewsItemClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var itemListener: OnNewsItemClickListener? = null

        init {
            this.itemListener = listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onNewsItemClick(adapterPosition)
        }
    }

    interface OnNewsItemClickListener {
        fun onNewsItemClick(position: Int)
    }
}