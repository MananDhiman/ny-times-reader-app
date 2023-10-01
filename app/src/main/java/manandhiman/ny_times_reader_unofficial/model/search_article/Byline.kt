package manandhiman.ny_times_reader_unofficial.model.search_article

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)