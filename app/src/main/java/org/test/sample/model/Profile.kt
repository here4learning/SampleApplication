package org.test.sample.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.Period

@SuppressLint("ParcelCreator")
@Parcelize
data class Profile (val firstName : String?,val lastName : String?,val age : Age?) : Parcelable