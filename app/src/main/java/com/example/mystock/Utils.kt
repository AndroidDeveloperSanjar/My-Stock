package com.example.mystock

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun log(msg: String) =
    Log.i("this_is_my_log",msg)


fun Fragment.toast(msg: String) =
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
