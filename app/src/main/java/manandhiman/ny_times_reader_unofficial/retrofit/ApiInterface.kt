package manandhiman.ny_times_reader_unofficial.retrofit

import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
  @GET("mostpopular/v2/viewed/{duration}.json")
  fun getPopularNews(
    @Path("duration") duration: String,
    @Query("api-key") apiKey:String): Call<PopularNewsApiResponse>

  @GET("search/v2/articlesearch.json")
  fun getArticle(
    @Query("fq") query: String,
    @Query("api-key") apiKey:String): Call<SearchArticleApiResponse>

  // for local testing
  @GET("popular")
  fun getPopularNews(): Call<PopularNewsApiResponse>

  @GET("article")
  fun getArticle(): Call<SearchArticleApiResponse>
}
