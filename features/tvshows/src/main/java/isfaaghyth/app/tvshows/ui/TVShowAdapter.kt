package isfaaghyth.app.tvshows.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import isfaaghyth.app.abstraction.util.ext.load
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.movie_details.MovieDetailActivity
import isfaaghyth.app.tvshows.R
import kotlinx.android.synthetic.main.item_tvshow.view.*

class TVShowAdapter(private val tvshows: List<TVShow>): RecyclerView.Adapter<TVShowAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.inflate(parent)
    }

    override fun getItemCount(): Int = tvshows.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(tvshows[position])
    }

    class Holder(private val view: View): RecyclerView.ViewHolder(view) {
        private val cardItem = view.card_movie
        private val poster = view.img_poster
        private val title = view.txt_movie_name
        private val year = view.txt_year

        companion object {
            fun inflate(parent: ViewGroup): Holder {
                return Holder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_tvshow,
                        parent,
                        false)
                )
            }
        }

        fun bind(tvshow: TVShow) {
            title.text = tvshow.title
            year.text = tvshow.releaseDate
            poster.load(tvshow.bannerUrl())
            cardItem.setOnClickListener {
                val context = view.context
                context.startActivity(MovieDetailActivity.tvShowIntent(context, tvshow))
            }
        }
    }

}