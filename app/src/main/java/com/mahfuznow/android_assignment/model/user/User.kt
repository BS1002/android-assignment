package com.mahfuznow.android_assignment.model.user

data class User(
    val info: Info,
    val results: List<Result>
)

/**
 ******** Response from https://randomuser.me/api?results=1 endpoint *********
{
    "results": [
        {
            "gender": "male",
            "name": {
                "title": "Mr",
                "first": "Cameron",
                "last": "Barnes"
            },
            "location": {
                "street": {
                    "number": 6334,
                    "name": "Saddle Dr"
                },
                "city": "Kalgoorlie",
                "state": "New South Wales",
                "country": "Australia",
                "postcode": 1706,
                "coordinates": {
                    "latitude": "-85.2372",
                    "longitude": "-20.4392"
                },
                "timezone": {
                    "offset": "-8:00",
                    "description": "Pacific Time (US & Canada)"
                }
            },
            "email": "cameron.barnes@example.com",
            "login": {
                "uuid": "202ab723-41b3-4639-addd-af29041ee44a",
                "username": "smallswan705",
                "password": "sonic",
                "salt": "teTpxlKz",
                "md5": "e9949dc5b76e6a9c8e3fb476423190f9",
                "sha1": "14d8cf26a66d4c1411b856c8680a57fd2b97a15b",
                "sha256": "f7d214070aa01942b57fe45eb0fe070685640fe3d27ee54fac8b395afa1a0aeb"
            },
            "dob": {
                "date": "1996-05-11T09:10:33.031Z",
                "age": 26
            },
            "registered": {
                "date": "2005-04-08T15:22:25.256Z",
                "age": 17
            },
            "phone": "00-2098-3266",
            "cell": "0406-282-302",
            "id": {
                "name": "TFN",
                "value": "124470663"
            },
            "picture": {
                "large": "https://randomuser.me/api/portraits/men/59.jpg",
                "medium": "https://randomuser.me/api/portraits/med/men/59.jpg",
                "thumbnail": "https://randomuser.me/api/portraits/thumb/men/59.jpg"
            },
            "nat": "AU"
        },
        {
            //Next User Result
        }
    ],
    "info": {
        "seed": "00f8e62d90a26d38",
        "results": 1,
        "page": 1,
        "version": "1.3"
    }
}
*/