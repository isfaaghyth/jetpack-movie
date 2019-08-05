package isfaaghyth.app.movie_details

import android.util.Log
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.toast

class MovieDetailActivity: BaseActivity() {

    override fun contentView(): Int = R.layout.activity_movie_detail

    override fun initView() {
        try {
            val json = intent?.data?.lastPathSegment as String
            Log.d("TAG", json)
        } catch (e: Exception) {
            toast("something wrong!")
            finish()
        }
    }

    override fun initInjector() = Unit //Nothing to inject

}