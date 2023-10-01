package manandhiman.ny_times_reader_unofficial.model.search_article

data class Multimedia(
    val caption: Any,
    val credit: Any,
    val crop_name: String,
    val height: Int,
    val legacy: Legacy,
    val rank: Int,
    val subType: String,
    val subtype: String,
    val type: String,
    val url: String,
    val width: Int
)