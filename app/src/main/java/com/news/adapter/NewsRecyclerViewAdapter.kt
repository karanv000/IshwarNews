package com.news.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.inflate
import com.news.utils.Utils
import com.news.models.SingleNewsItem
import com.news.views.activities.NewsDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_news_item.view.*
import java.lang.Exception

class NewsRecyclerViewAdapter(private val news: List<SingleNewsItem>) :
    RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemHolder>() {

    class NewsItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var newsItemDetails: SingleNewsItem? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindNews(newsItemDetails: SingleNewsItem) {
            this.newsItemDetails = newsItemDetails
            view.itemTitle.text = newsItemDetails.title
            view.itemDate.text = newsItemDetails.published_date
            try {
                val thumb = Utils.getThumbUrl(newsItemDetails)
                if(thumb!= null) {
                    Picasso.get().load(thumb)
                        .placeholder(R.drawable.logo)
                        .into(view.itemImage)
                }
            } catch (e: Exception) {
                print(e.toString())
            }
            try {
                view.newsSection.text = newsItemDetails.section
            } catch (e: Exception) {
                print(e.toString())
            }
            try {
                view.authorName.text = newsItemDetails.byline
            } catch (e: Exception) {
                print(e.toString())
            }
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val showPhotoIntent = Intent(context, NewsDetailsActivity::class.java)
            showPhotoIntent.putExtra(NEWS_ITEM, newsItemDetails)
            context.startActivity(showPhotoIntent)
        }

    }

    companion object {
        const val NEWS_ITEM = "NewsItem"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsItemHolder {
        val inflatedView = parent.inflate(R.layout.single_news_item, false)
        return NewsItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NewsItemHolder, position: Int) {
        val newsItem = news[position]
        holder.bindNews(newsItem)
    }

    override fun getItemCount(): Int {
        return news.size
    }

}

