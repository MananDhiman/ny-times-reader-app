package manandhiman.ny_times_reader_unofficial.model.search_article

data class Response(
    val docs: List<Doc>,
    val meta: Meta
)