package isfaaghyth.app.data.entity

data class MovieDetail(
    val id: String? = "",
    val movieId: String? = "",
    val title: String? = "",
    val posterPath: String = "",
    val overview: String? = "",
    val backdropPath: String = "",
    val voteCount: Int? = 0,
    val voteAverage: Float? = 0f,
    val releaseDate: String? = ""
)