package com.fsec3grp1.fseuser

data class UserLogin(var id: Int = -1,
                     var username: String,
                     var name: String,
                     var email: String,
                     var city: String,
                     var password: String,
                     var cpassword: String)