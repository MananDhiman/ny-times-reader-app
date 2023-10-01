package manandhiman.ny_times_reader_unofficial.retrofit

import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
//  // get Popular 1 day
//  @GET("mostpopular/v2/viewed/1.json")
////  fun getPopularNews(): Call<Popular>
//  fun getPopularNews(@Query("api-key") apiKey:String): Call<PopularNewsApiResponse>


  // for local testing

  @GET("popular")
  fun getPopularNews(): Call<PopularNewsApiResponse>

  @GET("article")
  fun getArticle(): Call<SearchArticleApiResponse>
}