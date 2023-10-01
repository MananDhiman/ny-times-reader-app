package manandhiman.ny_times_reader_unofficial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import manandhiman.ny_times_reader_unofficial.databinding.ActivityMainBinding
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult
import manandhiman.ny_times_reader_unofficial.model.search_article.Doc
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // remove get response on button click
    // add swipe refresh
    // add save last record to local db for reading
    // add save article to local db for permanent

    binding.button.setOnClickListener {
      Toast.makeText(this, "WORKS", Toast.LENGTH_LONG).show()

      fetchFromApi()
      Toast.makeText(this, "Finish", Toast.LENGTH_LONG).show()
    }

    binding.button2.setOnClickListener {
      Toast.makeText(this, "WORKS", Toast.LENGTH_LONG).show()

      fetchSingle()
      Toast.makeText(this, "Finish", Toast.LENGTH_LONG).show()
    }

    binding.recyclerView.layoutManager = LinearLayoutManager(this)

  }

  private fun fetchFromApi() {

//    val response = RetrofitInstance.getInstance().apiInterface.getPopularNews(R.string.api_key.toString())
    val response = RetrofitInstance.getInstance().apiInterface.getPopularNews()
    response.enqueue(object: Callback<PopularNewsApiResponse>{

      override fun onFailure(call: Call<PopularNewsApiResponse>, t: Throwable) {
        Log.d("response fail", t.message.toString())
        Toast.makeText(this@MainActivity, R.string.toast_err, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<PopularNewsApiResponse>, response: Response<PopularNewsApiResponse>) {
        popularHandleSuccessResponse(response) }

    })

  }

  private fun popularHandleSuccessResponse(response: Response<PopularNewsApiResponse>) {
    try {
      val listPopularNews: List<PopularNewsSingleResult> = response.body()!!.listPopularNews
      popularDisplayResults(listPopularNews)
    }
    catch (e: Exception) {
      Log.d("response try exce", e.message.toString())
      Log.d("response try exce", response.body().toString())
      Toast.makeText(this@MainActivity, R.string.toast_err, Toast.LENGTH_LONG).show()
    }
  }

  private fun popularDisplayResults(listPopularNews: List<PopularNewsSingleResult>) {
    Toast.makeText(this@MainActivity, "News Fetched", Toast.LENGTH_LONG).show()
    val adapter = PopularNewsRecyclerViewAdapter(listPopularNews)
    binding.recyclerView.adapter = adapter
  }

  private fun fetchSingle() {
    val response = RetrofitInstance.getInstance().apiInterface.getArticle()
    response.enqueue(object: Callback<SearchArticleApiResponse>{

      override fun onFailure(call: Call<SearchArticleApiResponse>, t: Throwable) {
        Log.d("response fail", t.message.toString())
        Toast.makeText(this@MainActivity, R.string.toast_err, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<SearchArticleApiResponse>, response: Response<SearchArticleApiResponse>) {
        handleSuccessResponse(response) }

    })
  }

  private fun handleSuccessResponse(response: Response<SearchArticleApiResponse>) {
    try {
      val listPopularNews: List<Doc> = response.body()!!.response.docs
      displayResults(listPopularNews)
    }
    catch (e: Exception) {
      Log.d("response try exce", e.message.toString())
      Toast.makeText(this@MainActivity, R.string.toast_err, Toast.LENGTH_LONG).show()
    }
  }

  private fun displayResults(listArticles: List<Doc>) {
    Toast.makeText(this@MainActivity, "News Fetched", Toast.LENGTH_LONG).show()
    val adapter = SearchedArticleRecyclerViewAdapter(listArticles)
    binding.recyclerView.adapter = adapter
  }



}
