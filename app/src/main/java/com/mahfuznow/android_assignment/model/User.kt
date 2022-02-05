package com.mahfuznow.android_assignment.model

import com.mahfuznow.android_assignment.model.userdata.Info
import com.mahfuznow.android_assignment.model.userdata.Result

data class User(
    val info: Info? = null,
    val results: List<Result>? = null
)