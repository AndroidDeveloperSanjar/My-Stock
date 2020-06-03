package com.example.mystock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystock.useCase.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    getUserInformation: GetUserInformation,
    getTotalValue: GetTotalValue,
    private val getRecommendedStock: GetRecommendedStock,
    getStocks: GetStocks
): ViewModel(){
    val userInformation: LiveData<UserInformation>
            = getUserInformation.get()

    val totalValue: LiveData<Int>
            = getTotalValue.get()

    val recommendedStock: LiveData<String>
            = getRecommendedStock.recommendedStock

    val stocks: LiveData<String>
            = getStocks.get()

    init {
        getRecommendedStock()
    }

    private fun getRecommendedStock(){
        viewModelScope.launch {
            getRecommendedStock.get()
        }
    }

    fun refreshRecommendedStock(){
        viewModelScope.launch {
            getRecommendedStock.refresh()
        }
    }

}