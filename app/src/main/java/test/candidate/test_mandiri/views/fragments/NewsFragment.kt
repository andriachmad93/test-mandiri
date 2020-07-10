package test.candidate.test_mandiri.views.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import test.candidate.test_mandiri.R
import test.candidate.test_mandiri.adapters.NewsAdapter
import test.candidate.test_mandiri.databinding.FragmentNewsBinding
import test.candidate.test_mandiri.models.NewsModel
import test.candidate.test_mandiri.utilities.InjectorUtils
import test.candidate.test_mandiri.viewmodels.NewsViewModel
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        linearLayoutManager = LinearLayoutManager(context)

        searchNews()

        return binding.root
    }

    private fun getNews(searchNews: String) {
        binding.pgrLoad.visibility = View.VISIBLE

        Handler().postDelayed({
            val factory = InjectorUtils.provideNewsViewModelFactory()

            newsViewModel = ViewModelProviders.of(this, factory).get(NewsViewModel::class.java)

            newsViewModel.getNews(searchNews).observe(viewLifecycleOwner,
                object : Observer<NewsModel> {
                    override fun onChanged(newsModel: NewsModel?) {
                        try {
                            adapter.submitList(newsModel?.articles)
                            binding.newsListSize = newsModel?.totalResults
                        } catch (err: Exception) {
                            Toast.makeText(context, err.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            )
        }, 3000)
    }

    private fun searchNews() {
        binding.edtNews.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                binding.newsListSize = 0
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                adapter = NewsAdapter(activity)

                binding.rcvNewsList.adapter = adapter
                binding.rcvNewsList.layoutManager = linearLayoutManager

                if (charSequence?.length == 0) {
                    binding.newsListSize = 0
                } else {
                    getNews(charSequence.toString())
                }
            }
        })
    }
}