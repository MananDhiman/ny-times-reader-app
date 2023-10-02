package manandhiman.ny_times_reader_unofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.adapters.SearchedArticleRecyclerViewAdapter
import manandhiman.ny_times_reader_unofficial.databinding.FragmentSearchNewsBinding
import manandhiman.ny_times_reader_unofficial.model.search_article.Doc
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchNewsFragment : Fragment() {
  private lateinit var binding: FragmentSearchNewsBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentSearchNewsBinding.inflate(layoutInflater, container, false)

    binding.button.setOnClickListener {
      prepareRequest()
    }

    return binding.root
  }

  private fun prepareRequest() {
    if(binding.editText.text.isEmpty()) return
    val q = binding.editText.text.toString()

    val qArr = q.split(" ")
    val query = qArr[0]

    makeSearchRequest(query)
  }

  private fun makeSearchRequest(query: String) {

    val apiKey = context?.getString(R.string.api_key)
    val response = RetrofitInstance.getInstance().apiInterface.getArticle(query,
      apiKey!!
    )

    response.enqueue(object: Callback<SearchArticleApiResponse> {

      override fun onFailure(call: Call<SearchArticleApiResponse>, t: Throwable) {
        Toast.makeText(context,R.string.toast_api_no_response, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<SearchArticleApiResponse>, response: Response<SearchArticleApiResponse>) {
        popularHandleSuccessResponse(response)
      }

    })
  }

  private fun popularHandleSuccessResponse(response: Response<SearchArticleApiResponse>) {
    try {
      val listPopularNews: List<Doc> = response.body()!!.response.docs
      popularDisplayResults(listPopularNews)
    }
    catch (e: Exception) {
      Toast.makeText(context, R.string.toast_api_got_response, Toast.LENGTH_LONG).show()
    }
  }

  private fun popularDisplayResults(listPopularNews: List<Doc>) {

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    val adapter = SearchedArticleRecyclerViewAdapter(listPopularNews)

    binding.recyclerView.adapter = adapter

  }

}