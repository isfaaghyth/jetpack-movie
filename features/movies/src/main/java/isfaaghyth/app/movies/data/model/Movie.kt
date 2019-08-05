package isfaaghyth.app.movies.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import isfaaghyth.app.movies.BuildConfig

data class Movies(
    @Expose @SerializedName("results") val resultsIntent: List<Movie>
)

data class Movie(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("movie_id") val movieId: String,
    @Expose @SerializedName("original_title") val title: String,
    @Expose @SerializedName("poster_path") val posterPath: String,
    @Expose @SerializedName("overview") val overview: String,
    @Expose @SerializedName("backdrop_path") val backdropPath: String,
    @Expose @SerializedName("release_date") val releaseDate: String
): Parcelable {

    fun bannerUrl(): String {
        return "${BuildConfig.IMAGE_URL}$backdropPath"
    }

    fun applinkMovie(): String {
        return "moviedb://detail/$id"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(movieId)
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(backdropPath)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}