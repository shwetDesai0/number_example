package com.example.mybaseapplication.activity.example1.model

import android.text.InputFilter
import android.text.Spanned

class NumberCommaInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (source == null) return null

        for (i in start until end) {
            val char = source[i]
            if (!char.isDigit() && char != ',') {
                return ""
            }
        }
        return null
    }
}