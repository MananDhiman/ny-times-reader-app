package manandhiman.ny_times_reader_unofficial.model.search_article

data class SearchArticleApiResponse(
    val copyright: String,
    val response: Response,
    val status: String
)