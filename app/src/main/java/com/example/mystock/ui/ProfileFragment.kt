package com.example.mystock.ui
import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.example.mystock.R
import com.example.mystock.log
import com.example.mystock.toast
import com.example.mystock.useCase.GetRecommendedStock
import com.example.mystock.useCase.GetStocks
import com.example.mystock.useCase.GetTotalValue
import com.example.mystock.useCase.GetUserInformation
import com.example.mystock.viewmodel.ProfileViewModel
import com.example.mystock.viewmodel.ProfileViewModelFactory
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers.IO

private const val PREFERENCE_IS_FIRST_PROFILE_LAUNCH = "preference_is_first_profile_launch"

class ProfileFragment : Fragment(R.layout.fragment_profile){

    private lateinit var viewModel: ProfileViewModel

    private lateinit var sharedPreferences: SharedPreferences

    init {
        lifecycleScope.launchWhenResumed {
            if (isFirstProfileLaunch()){
                toast("Welcome to Sanjar's Stock Info!")
                displayTipDialog()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setUpViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProfileInfo()
    }

    private fun init(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            requireContext()
        )
    }

    private fun setUpViewModel() {
        log("setUpViewModel()")
        val factory = ProfileViewModelFactory(
            GetUserInformation(),
            GetTotalValue(),
            GetRecommendedStock(IO),
            GetStocks()
        )
        viewModel = ViewModelProvider(
            this,
            factory
        )[ProfileViewModel::class.java]
    }

    private fun setUpProfileInfo() {
        log("setUpProfileInfo()")
        setUpUserInformation()
        setUpTotalValue()
        setUpRecommendedStock()
        setUpStocks()
    }

    private fun setUpUserInformation() {
        log("setUpUserInformation()")
        viewModel.userInformation.observe(
            viewLifecycleOwner,
            Observer {
                nameTextView.text = it.name
                accountNumberTextView.text = it.accountNumber
                phoneNumberTextView.text = it.phoneNumber
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setUpTotalValue() {
        log("setUpTotalValue()")
        viewModel.totalValue.observe(
            viewLifecycleOwner,
            Observer { total ->
                totalValueTextView.text =  "$$total"
            }
        )
    }

    private fun setUpRecommendedStock() {
        log("setUpRecommendedStock()")
        viewModel.recommendedStock.observe(
            viewLifecycleOwner,
            Observer {
                recommendedStockTextView.text = it
            }
        )
        refreshRecommendedStockButton.setOnClickListener {
            viewModel.refreshRecommendedStock()
        }
    }

    private fun setUpStocks(){
        log("setUpStocks()")
        viewModel.stocks.observe(
            viewLifecycleOwner,
            Observer {
                stocksTextView.text = it
            }
        )
    }

    private fun isFirstProfileLaunch() =  sharedPreferences.getBoolean(
        PREFERENCE_IS_FIRST_PROFILE_LAUNCH,
        true
    )

    private fun displayTipDialog() {
        log("displayTipDialog()")
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.profile_tip_title)
            .setMessage(R.string.profile_tip_message)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                recordProfileFirstLaunch()
            }
            .show()
    }

    private fun recordProfileFirstLaunch() =
        sharedPreferences
            .edit()
            .putBoolean(PREFERENCE_IS_FIRST_PROFILE_LAUNCH, false)
            .apply()
}