package manandhiman.ny_times_reader_unofficial.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    initSpinner()

    fetchFromApi()

    return binding.root
  }

  private fun initSpinner() {
    val timeDuration = arrayOf("1","3","5","7","10","15")
    val arrayAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, timeDuration)
    binding.spinner.adapter = arrayAdapter

    binding.spinner.onItemSelectedListener = object :
    AdapterView.OnItemSelectedListener {
      override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val durationSelected = binding.spinner.getItemAtPosition(p2).toString()
        getPostsOfDuration(durationSelected)
      }

      override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
      }
    }
  }

  private fun getPostsOfDuration(durationSelected: String) {
    val response = RetrofitInstance.getInstance().apiInterface.getPopularNews(durationSelected)
    response.enqueue(object: Callback<String> {

      override fun onFailure(call: Call<String>, t: Throwable) {
//        Log.d("response fail", t.message.toString())
        Toast.makeText(context, R.string.toast_err, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<String>, response: Response<String>) {
        Toast.makeText(requireContext(), response.body(), Toast.LENGTH_LONG).show() }

    })
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