package test.candidate.test_mandiri.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.candidate.test_mandiri.databinding.ItemNewsBinding
import test.candidate.test_mandiri.models.NewsDetailModel
import test.candidate.test_mandiri.models.NewsModel
import test.candidate.test_mandiri.views.activities.NewsDetailActivity

class NewsAdapter(private val activity: Activity?) : ListAdapter<NewsDetailModel, NewsAdapter.ViewHolder>(ViewHolder.NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context)
                , parent
                , false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsDetailItem = getItem(position)

        holder.apply {
            bind(
                setItemOnClickListener(newsDetailItem)
                , newsDetailItem
                , position
            )
        }
    }

    private fun setItemOnClickListener(newsItem: NewsDetailModel): View.OnClickListener {
        return View.OnClickListener {
            setIntentActivity(newsItem.url!!)
        }
    }

    private fun setIntentActivity(newsURL: String) {
        val intent = Intent(activity, NewsDetailActivity::class.java)

        intent.putExtra("NewsURL", newsURL)

        activity?.startActivity(intent)
    }

    class ViewHolder (private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var newsDetailModel: NewsDetailModel

        fun bind (
            setOnClick: View.OnClickListener
            , newsDetailModel: NewsDetailModel
            , position: Int
        ) {

            this.newsDetailModel = newsDetailModel

            binding.apply {
                clickListener = setOnClick
                authorName = "Author " + newsDetailModel.author
                titleNews = newsDetailModel.title
                descriptionNews = newsDetailModel.description
                publishedAt = newsDetailModel.publishedAt
            }

            getNewsImage()
        }

        private fun getNewsImage() {
            Glide.with(binding.newsImage)
                .load(newsDetailModel.urlToImage)
                .into(binding.newsImage)
        }

        class NewsDiffCallback : DiffUtil.ItemCallback<NewsDetailModel>() {
            override fun areItemsTheSame(oldItem: NewsDetailModel, newItem: NewsDetailModel): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: NewsDetailModel, newItem: NewsDetailModel): Boolean {
                return true
            }
        }
    }
}
