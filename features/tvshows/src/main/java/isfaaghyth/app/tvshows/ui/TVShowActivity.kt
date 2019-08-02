package isfaaghyth.app.tvshows.ui

import android.content.Context
import android.content.Intent
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.tvshows.R

class TVShowActivity: BaseActivity(), TVShowView {

    override fun contentView(): Int = R.layout.activity_tvshow

    override fun initView() {

    }

    override fun initInjector() {

    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, TVShowActivity::class.java)
        }
    }

}