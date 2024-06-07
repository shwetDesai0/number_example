package com.example.mybaseapplication.activity.example1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.core.extension.openFragmentCommit
import com.example.mybaseapplication.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Shwet Desai on 14/12/23.
 * All rights reserved.
 * shwet.desai0@gmail.com
 */

@AndroidEntryPoint
class NumberActivity : AppCompatActivity(R.layout.activity_number) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            openFragmentCommit(R.id.container, NumberFragment.newInstance())

    }

}