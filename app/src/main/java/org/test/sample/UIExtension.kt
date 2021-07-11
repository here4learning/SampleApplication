package org.test.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

const val visible: Int = View.VISIBLE
const val invisible: Int = View.INVISIBLE
const val gone: Int = View.GONE
infix fun View.visibility(type: Int) {
    visibility = type
}

infix fun ViewGroup.inflateLayout(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

const val forShortTime = Toast.LENGTH_SHORT
const val forLongTime = Toast.LENGTH_LONG
infix fun String?.showAsToast(duration: Int) {
    this?.run {
        Toast.makeText(App.getInstance(), this, duration).show()
    }
}

infix fun Int.showAsToast(duration: Int) {
    Toast.makeText(App.getInstance(), this, duration).show()
}
