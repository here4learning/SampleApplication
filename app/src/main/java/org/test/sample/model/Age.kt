package org.test.sample.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.Period

@SuppressLint("ParcelCreator")
@Parcelize
class Age(private val period : Period?) : Parcelable {
    override fun toString(): String {
        return "${period?.years} years ${period?.months} months ${period?.days} days"
    }
}