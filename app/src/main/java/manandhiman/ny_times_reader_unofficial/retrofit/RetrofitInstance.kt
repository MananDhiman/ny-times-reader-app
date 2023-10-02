package manandhiman.ny_times_reader_unofficial.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
//  private const val BASE_URL = "https://api.nytimes.com/svc/"
  private const val BASE_URL = "http://192.168.42.183:3000/"
  private var instance: RetrofitInstance? = null
  var apiInterface: ApiInterface

  init {
    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    apiInterface = retrofit.create(ApiInterface::class.java)
  }

  fun getInstance(): RetrofitInstance {
    if(instance == null) instance = RetrofitInstance
    return instance as RetrofitInstance
  }
}