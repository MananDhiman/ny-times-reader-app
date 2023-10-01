package manandhiman.ny_times_reader_unofficial.model.popular

import com.google.gson.annotations.SerializedName

data class PopularNewsSingleResult(
    val `abstract`: String,
    @SerializedName("adx_keywords") val adxKeywords: String,
    @SerializedName("asset_id") val assetId: Long,
    val byline: String,
    val column: Any,
    @SerializedName("des_facet") val desFacet: List<String>,
    @SerializedName("eta_id") val etaId: Int,
    @SerializedName("geo_facet") val geoFacet: List<String>,
    val id: Long,
    val media: List<Media>,
    @SerializedName("nytdsection") val nytDSection: String,
    @SerializedName("org_facet") val orgFacet: List<String>,
    @SerializedName("per_facet") val perFacet: List<String>,
    @SerializedName("published_date") val publishedDate: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
)