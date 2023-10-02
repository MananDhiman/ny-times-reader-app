package manandhiman.ny_times_reader_unofficial.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.adapters.PopularNewsRecyclerViewAdapter
import manandhiman.ny_times_reader_unofficial.adapters.SearchedArticleRecyclerViewAdapter
import manandhiman.ny_times_reader_unofficial.databinding.FragmentSearchNewsBinding
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult
import manandhiman.ny_times_reader_unofficial.model.search_article.Doc
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchNewsFragment : Fragment() {
  private lateinit var binding: FragmentSearchNewsBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentSearchNewsBinding.inflate(layoutInflater, container, false)

    fetchFromApi()

    binding.button.setOnClickListener {
      prepareRequest()
    }

    return binding.root
  }

  private fun prepareRequest() {
    if(binding.editText.text.isEmpty()) return
    val q = binding.editText.text.toString()

    val qArr = q.split(" ")

    val map = mutableMapOf<String, String>()
    for(i in qArr) map[i] = i
    Log.d("map", map.toString())
    Toast.makeText(requireContext(), "Making Req", Toast.LENGTH_SHORT).show()
    makeSearchRequest(map)
  }

  private fun makeSearchRequest(map: Map<String, String>) {
    val response = RetrofitInstance.getInstance().apiInterface.getArticle(map)
    response.enqueue(object: Callback<String> {

      override fun onFailure(call: Call<String>, t: Throwable) {
        Log.d("response fail", t.message.toString())
        Toast.makeText(context, R.string.toast_err, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<String>, response: Response<String>) {
        Toast.makeText(requireContext(), response.body(), Toast.LENGTH_LONG).show() }

    })
  }

  private fun fetchFromApi() {

    val response = RetrofitInstance.getInstance().apiInterface.getArticle()
    response.enqueue(object: Callback<SearchArticleApiResponse> {

      override fun onFailure(call: Call<SearchArticleApiResponse>, t: Throwable) {
//        Log.d("response fail", t.message.toString())
        Toast.makeText(context, R.string.toast_err, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<SearchArticleApiResponse>, response: Response<SearchArticleApiResponse>) {
        popularHandleSuccessResponse(response) }

    })

  }

  private fun popularHandleSuccessResponse(response: Response<SearchArticleApiResponse>) {
    try {
      val listPopularNews: List<Doc> = response.body()!!.response.docs
      popularDisplayResults(listPopularNews)
    }
    catch (e: Exception) {
//      Log.d("response try exce", e.message.toString())
//      Log.d("response try exce", response.body().toString())
      Toast.makeText(context, R.string.toast_err, Toast.LENGTH_LONG).show()
    }
  }

  private fun popularDisplayResults(listPopularNews: List<Doc>) {

    Toast.makeText(context, "News Fetched", Toast.LENGTH_LONG).show()

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    val adapter = SearchedArticleRecyclerViewAdapter(listPopularNews)

    binding.recyclerView.adapter = adapter

  }

}