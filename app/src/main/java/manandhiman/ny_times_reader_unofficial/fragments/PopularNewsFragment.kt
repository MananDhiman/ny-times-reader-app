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
import manandhiman.ny_times_reader_unofficial.databinding.FragmentPopularNewsBinding
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.StringBuilder

class PopularNewsFragment : Fragment() {
  private lateinit var binding: FragmentPopularNewsBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentPopularNewsBinding.inflate(layoutInflater, container, false)

    fetchFromApi()

    return binding.root
  }

  private fun fetchFromApi() {

    val response = RetrofitInstance.getInstance().apiInterface.getPopularNews()
    response.enqueue(object: Callback<PopularNewsApiResponse> {

      override fun onFailure(call: Call<PopularNewsApiResponse>, t: Throwable) {
//        Log.d("response fail", t.message.toString())
        Toast.makeText(context, R.string.toast_err, Toast.LENGTH_LONG).show() }

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
//      Log.d("response try exce", e.message.toString())
//      Log.d("response try exce", response.body().toString())
      Toast.makeText(context, R.string.toast_err, Toast.LENGTH_LONG).show()
    }
  }

  private fun popularDisplayResults(listPopularNews: List<PopularNewsSingleResult>) {

    Toast.makeText(context, "News Fetched", Toast.LENGTH_LONG).show()

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    val adapter = PopularNewsRecyclerViewAdapter(listPopularNews)

    binding.recyclerView.adapter = adapter

  }

}