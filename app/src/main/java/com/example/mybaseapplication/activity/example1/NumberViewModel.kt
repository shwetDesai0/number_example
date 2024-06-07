package com.example.mybaseapplication.activity.example1

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.extension.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Shwet Desai on 14/12/23.
 * All rights reserved.
 * shwet.desai0@gmail.com
 */

@HiltViewModel
class NumberViewModel @Inject constructor() :
    ViewModel() {

    private val _resultString = MutableStateFlow<String>("")
    val resultString = _resultString.asStateFlow()

    private val _vennData = MutableStateFlow<List<Set<Int>>>(emptyList())
    val vennData = _vennData.asStateFlow()

    fun calculate(list1String: String, list2String: String, list3String: String) {
        viewModelScope.safeLaunch({
            val list1 = mutableSetOf<Int>()
            val list2 = mutableSetOf<Int>()
            val list3 = mutableSetOf<Int>()

            list1String.split(",")
                .forEach { if (it.isNotEmpty() && it.isDigitsOnly()) list1.add(it.trim().toInt()) }
            list2String.split(",")
                .forEach { if (it.isNotEmpty() && it.isDigitsOnly()) list2.add(it.trim().toInt()) }
            list3String.split(",")
                .forEach { if (it.isNotEmpty() && it.isDigitsOnly()) list3.add(it.trim().toInt()) }

            _vennData.value = listOf(list1, list2, list3)

            val intersection = list1.intersect(list2).intersect(list3)
            val union = list1.union(list2).union(list3)
            val highestNumber = union.maxOrNull()
            val result = """
                Intersection: ${intersection.joinToString(", ")}
                Union: ${union.joinToString(", ")}
                Highest Number: $highestNumber
            """.trimIndent()
            _resultString.value = result
        }) { errorCode, message -> }
    }

}