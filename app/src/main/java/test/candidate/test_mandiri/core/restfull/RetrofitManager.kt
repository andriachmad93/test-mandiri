package test.candidate.test_mandiri.core.restfull

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.candidate.test_mandiri.core.APIList

class RetrofitManager {
    private lateinit var mRetrofit: Retrofit

    val service: APIList get() = mRetrofit.create(APIList::class.java)

    init {
        initRetrofit()
    }

    fun initRetrofit() {
        mRetrofit = Retrofit.Builder()
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://newsapi.org/v2/")
            .build()
    }

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return  okHttpClient
    }
}