package isfaaghyth.app.movies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShows(
    @Expose @SerializedName("results") val resultsIntent: List<TvShow>
)

data class TvShow(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("movie_id") val movieId: String,
    @Expose @SerializedName("original_title") val title: String,
    @Expose @SerializedName("poster_path") val posterPath: String,
    @Expose @SerializedName("overview") val overview: String,
    @Expose @SerializedName("backdrop_path") val backdropPath: String,
    @Expose @SerializedName("release_date") val releaseDate: String
)