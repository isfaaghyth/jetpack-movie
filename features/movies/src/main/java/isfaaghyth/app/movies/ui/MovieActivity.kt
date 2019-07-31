package isfaaghyth.app.movies.ui

import android.content.Context
import android.content.Intent
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.movies.R

class MovieActivity: BaseActivity(), MovieView {

    override fun contentView(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun initInjector() {

    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, MovieActivity::class.java)
        }
    }

}