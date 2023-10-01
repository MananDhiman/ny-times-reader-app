package manandhiman.ny_times_reader_unofficial.model.popular

import com.google.gson.annotations.SerializedName
import manandhiman.ny_times_reader_unofficial.model.search_article.MediaMetadata

data class Media(
    @SerializedName("approved_for_syndication") val approvedForSyndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata") val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)