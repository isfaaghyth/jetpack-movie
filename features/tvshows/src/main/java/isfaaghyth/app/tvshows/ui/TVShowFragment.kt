package isfaaghyth.app.tvshows.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.tvshows.R
import isfaaghyth.app.tvshows.di.DaggerTVShowComponent
import kotlinx.android.synthetic.main.fragment_tvshow.*
import me.ibrahimyilmaz.kiel.adapterOf
import javax.inject.Inject

class TVShowFragment : Fragment() {

    private fun contentView(): Int = R.layout.fragment_tvshow

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TVShowViewModel

    private val adapter = adapterOf<TVShow> {
        register(
            layoutResource = R.layout.item_tvshow,
            viewHolder = ::TVShowHolder,
            onBindViewHolder = { tvShowHolder, _, tvshow ->
                tvShowHolder.cardItem.setOnClickListener {
                    requireContext().startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("jetmovie://detail/tv/${tvshow.id}")
                        )
                    )
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(TVShowViewModel::class.java)

        lstTvShows.adapter = adapter

        initObserver()
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is LoaderState.ShowLoading -> toast("loading")
                is LoaderState.HideLoading -> toast("complete")
            }
        })

        viewModel.result.observe(this, Observer(adapter::submitList))

        viewModel.error.observe(this, Observer { error ->
            toast(error)
        })
    }

    private fun initInjector() {
        DaggerTVShowComponent.builder()
            .tVShowModule(TVShowModule())
            .build()
            .inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clear()
    }

}