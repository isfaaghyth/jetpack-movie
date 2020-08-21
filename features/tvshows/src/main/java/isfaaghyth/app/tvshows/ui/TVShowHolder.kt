package isfaaghyth.app.tvshows.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import isfaaghyth.app.abstraction.util.ext.load
import isfaaghyth.app.data.entity.TVShow
import kotlinx.android.synthetic.main.item_tvshow.view.*
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class TVShowHolder(private val view: View) : RecyclerViewHolder<TVShow>(view) {
    val title = view.txt_movie_name
    val cardItem = view.card_movie
    val poster = view.img_poster
    val year = view.txt_year

    override fun bind(position: Int, item: TVShow) {
        super.bind(position, item)
        title.text = item.title
        year.text = item.releaseDate
        poster.load(item.bannerUrl())
    }
}