package com.core.extension

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * Created by Shwet Desai on 14/12/23.
 * All rights reserved.
 * shwet.desai0@gmail.com
 */


const val INTERNET_NOT_FOUND = 502
const val UNKNOWN_ERROR = 999

fun CoroutineScope.safeLaunch(
    launchBody: suspend () -> Unit,
    errorBody: (errorCode: Int, message: String) -> Unit
): Job {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

        errorBody.invoke(UNKNOWN_ERROR, "Something Went wrong")

        Log.d("SAFE_LAUNCH", throwable.stackTraceToString())
    }
    return this.launch(coroutineExceptionHandler) {
        launchBody.invoke()
    }
}
