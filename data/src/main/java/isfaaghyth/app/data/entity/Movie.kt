package isfaaghyth.app.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import isfaaghyth.app.data.BuildConfig

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
    @Expose @SerializedName("vote_count") val voteCount: Int,
    @Expose @SerializedName("vote_average") val voteAverage: Float,
    @Expose @SerializedName("release_date") val releaseDate: String
): Parcelable {

    fun bannerUrl() = "${BuildConfig.IMAGE_URL}$backdropPath"

    fun posterUrl() = "${BuildConfig.IMAGE_URL}$posterPath"

    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString()?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(movieId)
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(backdropPath)
        parcel.writeInt(voteCount)
        parcel.writeFloat(voteAverage)
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