package com.mahfuznow.android_assignment.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dob(
    val age: Int,
    val date: String
) : Parcelable