package isfaaghyth.app.data.entity

data class MovieDetail(
    val id: String,
    val movieId: String,
    val title: String,
    val posterPath: String,
    val overview: String,
    val backdropPath: String,
    val voteCount: Int,
    val voteAverage: Float,
    val releaseDate: String
)