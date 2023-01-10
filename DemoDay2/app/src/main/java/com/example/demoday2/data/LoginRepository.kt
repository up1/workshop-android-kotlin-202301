package com.example.demoday2.data

import com.example.demoday2.data.model.LoggedInUser
import com.example.demoday2.data.remote.LoginRequest
import com.example.demoday2.data.remote.UserApi
import java.io.IOException

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val request = LoginRequest(username, password)
            val result = UserApi.getApi()?.loginUser(request)
            if ((result?.code() == 200) && (result.body()?.code == 0)) {
                val user = LoggedInUser(
                    result.body()?.data?.Id.toString(),
                    result.body()?.data?.Name.toString()
                )
                return Result.Success(user)
            } else {
                return Result.Error(IOException("Error"))
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}