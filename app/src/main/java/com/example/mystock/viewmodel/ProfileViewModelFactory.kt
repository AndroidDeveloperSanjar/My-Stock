@file:Suppress("UNCHECKED_CAST")

package com.example.mystock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mystock.useCase.GetRecommendedStock
import com.example.mystock.useCase.GetStocks
import com.example.mystock.useCase.GetTotalValue
import com.example.mystock.useCase.GetUserInformation

class ProfileViewModelFactory(
    private val getUserInformation: GetUserInformation,
    private val getTotalValue: GetTotalValue,
    private val getRecommendedStock: GetRecommendedStock,
    private val getStocks: GetStocks
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
         ProfileViewModel(
             getUserInformation,
             getTotalValue,
             getRecommendedStock,
             getStocks
         ) as T

}