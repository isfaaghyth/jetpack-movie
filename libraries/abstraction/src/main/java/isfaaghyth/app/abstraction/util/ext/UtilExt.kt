package isfaaghyth.app.abstraction.util.ext

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by isfaaghyth on 29/04/19.
 * github: @isfaaghyth
 */

fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}