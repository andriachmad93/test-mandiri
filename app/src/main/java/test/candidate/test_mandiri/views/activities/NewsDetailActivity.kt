package test.candidate.test_mandiri.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import test.candidate.test_mandiri.R
import test.candidate.test_mandiri.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)

        val webData = intent.getStringExtra("NewsURL").toString()

        binding.newsURL.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)

                return true
            }
        }

        binding.newsURL.loadUrl(webData)
        binding.newsURL.settings.builtInZoomControls = true
        binding.newsURL.settings.useWideViewPort = true
    }
}