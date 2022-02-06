package com.mahfuznow.android_assignment.model.country

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    val name: String,
    val capital: String?,
    @Embedded
    val flags: Flags
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}


/**
    ******** Response from https://restcountries.com/v2/ endpoint *********
    [
        {
            "name": "Afghanistan",
            "capital": "Kabul",
            "flags": {
                        "svg": "https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_the_Taliban.svg",
                        "png": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_the_Taliban.svg/320px-Flag_of_the_Taliban.svg.png"
           }
        },
        {
            "name": "Albania",
            "capital": "Tirana",
            "flags": {
                        "svg": "https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_the_Taliban.svg",
                        "png": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_the_Taliban.svg/320px-Flag_of_the_Taliban.svg.png"
           }
        }
    ]
*/

