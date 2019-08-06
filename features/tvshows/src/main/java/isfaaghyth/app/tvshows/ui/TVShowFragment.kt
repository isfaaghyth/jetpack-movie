package isfaaghyth.app.tvshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import isfaaghyth.app.abstraction.util.toast
import isfaaghyth.app.data.TVShow
import isfaaghyth.app.tvshows.R
import isfaaghyth.app.tvshows.di.DaggerTVShowComponent
import kotlinx.android.synthetic.main.fragment_tvshow.*
import javax.inject.Inject

class TVShowFragment: Fragment() {

    private fun contentView(): Int = R.layout.fragment_tvshow

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TVShowViewModel

    private var tvshowData = arrayListOf<TVShow>()
    private val adapter: TVShowAdapter by lazy {
        TVShowAdapter(tvshowData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        //view model provider
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(TVShowViewModel::class.java)

        //set adapter
        lstTvShows.adapter = adapter

        //get movies
        initObserver()
    }

    private fun initObserver() {
        //observe it!
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is TVShowState.ShowLoading -> toast("loading")
                is TVShowState.HideLoading -> toast("complete")
                is TVShowState.LoadSuccess -> {
                    tvshowData.addAll(state.data.resultsIntent)
                    adapter.notifyDataSetChanged()
                }
                is TVShowState.MovieError -> {
                    toast("there's problem, please try again")
                }
            }
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