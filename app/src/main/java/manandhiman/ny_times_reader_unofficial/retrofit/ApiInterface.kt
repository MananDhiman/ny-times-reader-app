package manandhiman.ny_times_reader_unofficial.retrofit

import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap




interface ApiInterface {
//   get Popular 1 day
  @GET("mostpopular/v2/viewed/1.json")
  fun lgetPopularNews(@Query("api-key") apiKey:String): Call<PopularNewsApiResponse>

  // get popular time days
  @GET("mostpopular/v2/viewed/{duration}.json")
  fun lgetPopularNews(@Path("duration") duration: Int, @Query("api-key") apiKey:String): Call<PopularNewsApiResponse>



  // for local testing
  @GET("popular")
  fun getPopularNews(): Call<PopularNewsApiResponse>

  @GET("popular/{duration}")
  fun getPopularNews(@Path("duration") duration: String): Call<String>

  @GET("article")
  fun getArticle(): Call<SearchArticleApiResponse>

  @GET("article/q")
  fun getArticle(@QueryMap options: Map<String, String>): Call<String>


}