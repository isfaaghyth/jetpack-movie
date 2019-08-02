package isfaaghyth.app.abstraction.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import isfaaghyth.app.abstraction.util.KeyboardUtils
import isfaaghyth.app.abstraction.util.toast

abstract class BaseFragment: Fragment(), BaseView {

    /**
     * lifecycle method
     * @method contentView(): @return resLayoutId
     * @method initView()
     */
    abstract fun contentView(): Int
    abstract fun initView()
    abstract fun initInjector()

    /**
     * (optional, use it if needed)
     */
    protected lateinit var savedInstanceState: Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState
        }
        initInjector()
        initView()
    }

    override fun onMessage(message: String?) {
        toast(message)
    }

    override fun onMessage(stringResId: Int) {
        onMessage(getString(stringResId))
    }

    /**
     * check internet connection
     */
    override fun isNetworkConnect(): Boolean {
        return true //TODO(make a utilities class for this)
    }

    /**
     * hide keyboard layout
     */
    override fun hideKeyboard() {
        activity?.let {
            KeyboardUtils().hide(it)
        }
    }

}