package manandhiman.ny_times_reader_unofficial.model.popular

import com.google.gson.annotations.SerializedName

data class PopularNewsApiResponse(
  val copyright: String,
  @SerializedName("num_results") val numResults: Int,
  @SerializedName("results") val listPopularNews: List<PopularNewsSingleResult>,
  val status: String
)