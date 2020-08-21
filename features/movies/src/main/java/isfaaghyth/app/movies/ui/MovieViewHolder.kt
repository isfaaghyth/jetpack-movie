package isfaaghyth.app.movies.ui

import android.view.View
import isfaaghyth.app.abstraction.util.ext.load
import isfaaghyth.app.data.entity.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class MovieViewHolder(view: View) : RecyclerViewHolder<Movie>(view) {
    val title = view.txt_movie_name
    val cardItem = view.card_movie
    val poster = view.img_poster
    val year = view.txt_year

    override fun bind(position: Int, movie: Movie) {
        super.bind(position, movie)
        title.text = movie.title
        year.text = movie.releaseDate
        poster.load(movie.bannerUrl())
    }
}