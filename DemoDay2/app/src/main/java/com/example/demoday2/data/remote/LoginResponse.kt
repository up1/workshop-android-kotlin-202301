package com.example.demoday2.data.remote

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data? = Data()
)

data class Data(
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Email") var Email: String? = null,
    @SerializedName("Token") var Token: String? = null
)
