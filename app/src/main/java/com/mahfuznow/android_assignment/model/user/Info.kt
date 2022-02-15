package com.mahfuznow.android_assignment.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
) : Parcelable