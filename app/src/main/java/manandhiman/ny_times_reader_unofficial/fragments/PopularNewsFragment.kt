package manandhiman.ny_times_reader_unofficial.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.adapters.PopularRVAdapter
import manandhiman.ny_times_reader_unofficial.databinding.FragmentPopularNewsBinding
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularNewsFragment : Fragment() {

  private lateinit var binding: FragmentPopularNewsBinding
  private lateinit var loadingDialog: AlertDialog

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentPopularNewsBinding.inflate(layoutInflater, container, false)

    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    builder.setCancelable(false)
    builder.setView(R.layout.loading_dialog)

    loadingDialog = builder.create()

    initSpinner()

    return binding.root
  }

  private fun initSpinner() {

    val timePeriods = arrayOf("-","1","7","30")
    val spinnerAdapter = ArrayAdapter(
      requireContext(),
      com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
      timePeriods)

    binding.spinner.adapter = spinnerAdapter

    binding.spinner.onItemSelectedListener = object :
    AdapterView.OnItemSelectedListener {
      override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        val timePeriodSelected = binding.spinner.getItemAtPosition(p2).toString()

        if(timePeriodSelected != "-") makeHttpRequest(timePeriodSelected)

      }

      override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
      }
    }
  }

  private fun makeHttpRequest(durationSelected: String) {
    loadingDialog.show()
    val apiKey = context?.getString(R.string.api_key)
    val response = RetrofitInstance.getInstance().apiInterface.getPopularNews(
      durationSelected,
      apiKey!!
    )
    response.enqueue(object: Callback<PopularNewsApiResponse> {

      override fun onFailure(call: Call<PopularNewsApiResponse>, t: Throwable) {
        Toast.makeText(context, R.string.toast_api_no_response, Toast.LENGTH_LONG).show() }

      override fun onResponse(call: Call<PopularNewsApiResponse>, response: Response<PopularNewsApiResponse>) {

        handleApiResponse(response) }

    })
  }

  private fun handleApiResponse(response: Response<PopularNewsApiResponse>) {
    try {
      val listPopularNews: List<PopularNewsSingleResult> = response.body()!!.listPopularNews

      generateRecyclerView(listPopularNews)
    }
    catch (e: Exception) {
      Log.d("try fail response body", response.body().toString())
      Toast.makeText(context, R.string.toast_api_got_response, Toast.LENGTH_LONG).show()
    }
    loadingDialog.dismiss( )
  }

  private fun generateRecyclerView(listPopularNews: List<PopularNewsSingleResult>) {

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    val adapter = PopularRVAdapter(listPopularNews)

    binding.recyclerView.adapter = adapter

  }

}