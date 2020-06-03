package com.example.mystock.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mystock.log
import kotlinx.coroutines.delay

private const val GET_USER_INFORMATION_DELAY = 1_000L
private const val USER_NAME = "Sanjar Karimov"
private const val ACCOUNT_NUMBER = "4250_qwer"
private const val PHONE_NUMBER = "(99) 856-42-50"

class GetUserInformation {
    fun get(): LiveData<UserInformation> = liveData {
        delay(GET_USER_INFORMATION_DELAY)
        log("GetUserInformation Class")
        emit(
            UserInformation(
                name = USER_NAME,
                accountNumber = ACCOUNT_NUMBER,
                phoneNumber = PHONE_NUMBER
            )
        )
    }
}

data class UserInformation(
    val name: String,
    val accountNumber: String,
    val phoneNumber: String
)