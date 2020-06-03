package com.example.mystock.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import kotlin.random.Random

private const val TOTAL_VALUE_UPDATE_RATE_MS = 2_000L

class GetTotalValue {

    fun get() : LiveData<Int> = liveData {
        while (true){
            val total = Random.nextInt()
            emit(total)
            delay(TOTAL_VALUE_UPDATE_RATE_MS)
        }
    }

}