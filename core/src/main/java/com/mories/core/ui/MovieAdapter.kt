package com.mories.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mories.core.R
import com.mories.core.domain.model.MovieDomainModel
import kotlinx.android.synthetic.main.items_movie.view.*
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieDomainModel>()
    var onItemClick: ((MovieDomainModel) -> Unit)? = null

    fun setData(newListData: List<MovieDomainModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: MovieDomainModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + data.backdropPath)
                    .into(item_img_movie)
                item_title_movie.text = data.title
                item_popularity_movie.text = data.popularity.toString()
                item_overview_movie.text = data.overview
            }
        }

        init {
            view.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}