package manandhiman.ny_times_reader_unofficial.model.search_article

data class Keyword(
    val major: String,
    val name: String,
    val rank: Int,
    val value: String
)