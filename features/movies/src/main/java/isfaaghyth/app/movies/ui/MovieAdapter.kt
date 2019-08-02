package isfaaghyth.app.movies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import isfaaghyth.app.abstraction.util.load
import isfaaghyth.app.movies.BuildConfig
import isfaaghyth.app.movies.R
import isfaaghyth.app.movies.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movie: List<Movie>): RecyclerView.Adapter<MovieAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.inflate(parent)
    }

    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movie[position])
    }

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val cardItem = view.card_movie
        private val poster = view.img_poster
        private val title = view.txt_movie_name
        private val year = view.txt_year

        companion object {
            fun inflate(parent: ViewGroup): Holder {
                return Holder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_movie,
                        parent,
                        false)
                )
            }
        }

        fun bind(movie: Movie) {
            cardItem.setOnClickListener {  }
            poster.load("${BuildConfig.IMAGE_URL}${movie.backdropPath}")
            title.text = movie.title
            year.text = movie.releaseDate
        }
    }

}