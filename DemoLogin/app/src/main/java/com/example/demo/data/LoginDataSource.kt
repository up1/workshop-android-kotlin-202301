package com.example.demo.data

import com.example.demo.data.model.LoggedInUser
import com.example.demo.data.remote.LoginRequest
import com.example.demo.data.remote.UserApi
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val request = LoginRequest(username, password)
            val result = UserApi.getApi()?.loginUser(request)
            if (result?.code() == 200 && result.body()?.code == 0) {
                val user = LoggedInUser(
                    result.body()?.data?.id.toString(),
                    result.body()?.data?.name.toString()
                )
                return Result.Success(user)
            } else {
                return Result.Error(IOException("Error"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}