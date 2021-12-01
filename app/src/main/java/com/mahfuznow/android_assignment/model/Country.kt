package com.mahfuznow.android_assignment.model

data class Country(val name: String, val capital: String, val flags: Flags) {
    data class Flags(val svg: String, val png: String) {}
}


/*
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

