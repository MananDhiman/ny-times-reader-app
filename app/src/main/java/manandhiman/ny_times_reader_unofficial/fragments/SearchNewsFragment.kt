package manandhiman.ny_times_reader_unofficial.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.adapters.SearchedRVAdapter
import manandhiman.ny_times_reader_unofficial.databinding.FragmentSearchNewsBinding
import manandhiman.ny_times_reader_unofficial.model.search_article.Doc
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchNewsFragment : Fragment() {
  private lateinit var binding: FragmentSearchNewsBinding
  private lateinit var loadingDialog: AlertDialog
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentSearchNewsBinding.inflate(layoutInflater, container, false)

    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    builder.setCancelable(false)
    builder.setView(R.layout.loading_dialog)

    loadingDialog = builder.create()

    binding.button.setOnClickListener {
      handleFindButton()
    }

    return binding.root
  }

  private fun handleFindButton() {
    val query: String = binding.editText.text.toString()

    if(query.isEmpty())
      Toast.makeText(requireContext(),"Enter Text to Search", Toast.LENGTH_SHORT).show()
    else
      makeHttpRequest(query)

  }

  private fun makeHttpRequest(query: String) {
    loadingDialog.show()

    val apiKey = context?.getString(R.string.api_key)
    val response = RetrofitInstance.getInstance().apiInterface.getArticle(
      query,
      apiKey!!
    )

    response.enqueue(object: Callback<SearchArticleApiResponse> {

      override fun onFailure(call: Call<SearchArticleApiResponse>, t: Throwable) {
        Toast.makeText(context,R.string.toast_api_no_response, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<SearchArticleApiResponse>, response: Response<SearchArticleApiResponse>) {
        handleApiResponse(response)
      }

    })
  }

  private fun handleApiResponse(response: Response<SearchArticleApiResponse>) {
    try {
      val listSearchedArticles: List<Doc> = response.body()!!.response.docs
      generateRecyclerView(listSearchedArticles)
    }
    catch (e: Exception) {
      Toast.makeText(context, R.string.toast_api_got_response, Toast.LENGTH_LONG).show()
    }
    loadingDialog.dismiss( )
  }

  private fun generateRecyclerView(list: List<Doc>) {

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    val adapter = SearchedRVAdapter(list)

    binding.recyclerView.adapter = adapter

  }

}